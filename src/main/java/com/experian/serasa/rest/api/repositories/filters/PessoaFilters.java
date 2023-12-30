package com.experian.serasa.rest.api.repositories.filters;

import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import org.springframework.data.jpa.domain.Specification;

public class PessoaFilters {
  public static Specification<Pessoa> nomeLike(String nome) {
    return (root, query, criteriaBuilder) ->
        nome == null ? null : criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
  }

  public static Specification<Pessoa> cepEqual(String cep) {
    return (root, query, criteriaBuilder) ->
        cep == null ? null : criteriaBuilder.equal(root.get("cep"), cep);
  }

  public static Specification<Pessoa> idadeEqual(Integer idade) {
    return (root, query, criteriaBuilder) ->
        idade == null ? null : criteriaBuilder.equal(root.get("idade"), idade);
  }
}
