package com.experian.serasa.rest.api.services;

import static com.experian.serasa.rest.api.domain.pessoa.TabelaScore.getScoreStatus;

import com.experian.serasa.rest.api.consumers.CepConsumer;
import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import com.experian.serasa.rest.api.domain.pessoa.PessoaResponseDTO;
import com.experian.serasa.rest.api.domain.pessoa.RegisterDTO;
import com.experian.serasa.rest.api.repositories.PessoaRepository;
import com.experian.serasa.rest.api.repositories.filters.PessoaFilters;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
  private PessoaRepository pessoaRepository;
  @Autowired private CepConsumer cepConsumer;

  public PessoaService(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
  }

  public List<PessoaResponseDTO> findPessoas(String nome, Integer idade, String cep) {
    return pessoaRepository
        .findAll(
            Specification.where(PessoaFilters.nomeLike(nome))
                .and(PessoaFilters.idadeEqual(idade))
                .and(PessoaFilters.cepEqual(cep)))
        .stream()
        .map(pessoa -> new PessoaResponseDTO(pessoa, getScoreStatus(pessoa.getScore())))
        .toList();
  }

  public ResponseEntity<String> deletePessoa(String login) {
    Pessoa pessoa = (Pessoa) pessoaRepository.findByLogin(login);
    if (pessoa != null) {
      pessoaRepository.delete(pessoa);
      return ResponseEntity.ok("Pessoa excluída com sucesso.");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
    }
  }

  public ResponseEntity<String> updatePessoa(RegisterDTO data) {
    Pessoa existingPessoa = pessoaRepository.findByLogin(data.login());

    if (existingPessoa == null) {
      return ResponseEntity.notFound().build();
    }

    existingPessoa.setNome(data.nome());
    existingPessoa.setIdade(data.idade());
    existingPessoa.setCep(data.cep());

    var cepResponse = cepConsumer.get(data.cep());
    if (cepResponse == null) {
      return ResponseEntity.badRequest().build();
    }

    existingPessoa.setUf(cepResponse.getUf());
    existingPessoa.setLocalidade(cepResponse.getLocalidade());
    existingPessoa.setBairro(cepResponse.getBairro());
    existingPessoa.setLogradouro(cepResponse.getLogradouro());

    pessoaRepository.save(existingPessoa);
    return ResponseEntity.ok("Pessoa atualizada com sucesso.");
  }

  public ResponseEntity<String> registerPessoa(RegisterDTO data) {
    int score = data.score();
    if (score < 0 || score > 1000) {
      return ResponseEntity.badRequest().body("Score deve estar entre 0 e 1000.");
    }

    if (data.login() == null || data.login().isEmpty()) {
      return ResponseEntity.badRequest().body("O login não pode ser nulo ou vazio.");
    }

    if (data.password() == null || data.password().isEmpty()) {
      return ResponseEntity.badRequest().body("A senha não pode ser vazia.");
    }

    var cepResponse = cepConsumer.get(data.cep());
    if (cepResponse == null) {
      return ResponseEntity.badRequest().body("Falha ao obter dados do CEP.");
    }

    if (pessoaRepository.findByLogin(data.login()) != null) {
      return ResponseEntity.badRequest().body("Login já existente. Escolha outro login.");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    Pessoa newPessoa =
        new Pessoa(
            data.login(),
            encryptedPassword,
            data.nome(),
            data.idade(),
            data.cep(),
            cepResponse.getUf(),
            cepResponse.getLocalidade(),
            cepResponse.getBairro(),
            cepResponse.getLogradouro(),
            data.telefone(),
            data.score(),
            data.role());

    pessoaRepository.save(newPessoa);
    return ResponseEntity.ok("Pessoa cadastrada com sucesso.");
  }
}
