package com.experian.serasa.rest.api.controllers;

import com.experian.serasa.rest.api.consumers.CepConsumer;
import com.experian.serasa.rest.api.domain.pessoa.AuthenticationDTO;
import com.experian.serasa.rest.api.domain.pessoa.LoginResponseDTO;
import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import com.experian.serasa.rest.api.domain.pessoa.RegisterDTO;
import com.experian.serasa.rest.api.infra.security.TokenService;
import com.experian.serasa.rest.api.repositories.PessoaRepository;
import com.experian.serasa.rest.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private PessoaService pessoaService;
  @Autowired private PessoaRepository pessoaRepository;
  @Autowired private CepConsumer cepConsumer;
  @Autowired private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((Pessoa) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerPessoa(@RequestBody @Validated RegisterDTO data) {
    return pessoaService.registerPessoa(data);
  }

  @GetMapping("/pessoa")
  public ResponseEntity getByNome(
      @RequestParam(required = false) String nome,
      @RequestParam(required = false) Integer idade,
      @RequestParam(required = false) String cep) {
    var response = pessoaService.findPessoas(nome, idade, cep);
    return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
  }

  @PutMapping("/pessoa/{login}")
  public ResponseEntity<String> updatePessoa(
      @PathVariable String login, @RequestBody @Validated RegisterDTO data) {
    data.login();
    return pessoaService.updatePessoa(data);
  }

  @DeleteMapping("/pessoa/{login}")
  public ResponseEntity<String> deletePessoa(@PathVariable String login) {
    return pessoaService.deletePessoa(login);
  }
}
