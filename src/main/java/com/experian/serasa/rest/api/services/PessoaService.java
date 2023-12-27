package com.experian.serasa.rest.api.services;

import com.experian.serasa.rest.api.repositories.PessoaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements UserDetailsService {

  private final PessoaRepository repository;

  public PessoaService(PessoaRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByLogin(username);
  }
}
