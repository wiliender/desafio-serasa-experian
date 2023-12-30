package com.experian.serasa.rest.api.domain.pessoa;

public record PessoaResponseDTO(
    String nome,
    int idade,
    String cep,
    String estado,
    String cidade,
    String bairro,
    String logradouro,
    String telefone,
    int score,
    String scoreStatus) {
  public PessoaResponseDTO(Pessoa pessoa, String scoreStatus) {
    this(
        pessoa.getNome(),
        pessoa.getIdade(),
        pessoa.getCep(),
        pessoa.getEstado(),
        pessoa.getCidade(),
        pessoa.getBairro(),
        pessoa.getLogradouro(),
        pessoa.getTelefone(),
        pessoa.getScore(),
        scoreStatus);
  }
}
