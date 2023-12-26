package com.experian.serasa.rest.api.repositories;

import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
  UserDetails findByNome(String nome);

  UserDetails findByIdade(int idade);

  UserDetails findByCep(String cep);
}
