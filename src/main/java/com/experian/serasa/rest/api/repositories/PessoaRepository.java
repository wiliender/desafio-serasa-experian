package com.experian.serasa.rest.api.repositories;

import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {

  Pessoa findByLogin(String login);

  List<Pessoa> findAll(Specification filter);
}
