package com.experian.serasa.rest.api.domain.pessoa;

public record RegisterDTO(String login, String password, String nome, int idade, String cep, String estado, String cidade, String bairro, String logradouro, String telefone, int score, PessoaRole role) {

}

