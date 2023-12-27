package com.experian.serasa.rest.api.controllers;

import com.experian.serasa.rest.api.domain.pessoa.AuthenticationDTO;
import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import com.experian.serasa.rest.api.domain.pessoa.RegisterDTO;
import com.experian.serasa.rest.api.repositories.PessoaRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.pessoaRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Pessoa newPessoa;
        newPessoa = new Pessoa(data.login(), encryptedPassword, data.nome(), data.idade(), data.cep(), data.estado(), data.cidade(), data.bairro(), data.logradouro(), data.telefone(),data.score(),data.role());

        this.pessoaRepository.save(newPessoa);
        return ResponseEntity.ok().build();
    }
}
