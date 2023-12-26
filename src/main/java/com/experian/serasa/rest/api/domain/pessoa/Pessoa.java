package com.experian.serasa.rest.api.domain.pessoa;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "pessoas")
@Entity(name = "pessoas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String login;
  private String password;
  private String nome;
  private int idade;
  private String cep;
  private String estado;
  private String cidade;
  private String bairro;
  private String logradouro;
  private String telefone;
  private int score;
  private PessoaRole role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == PessoaRole.ADMIN)
      return List.of(
          new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public Pessoa(
      String login,
      String password,
      String nome,
      int idade,
      String cep,
      String estado,
      String cidade,
      String bairro,
      String logradouro,
      String telefone,
      int score,
      PessoaRole role) {

    this.login = login;
    this.password = password;
    this.nome = nome;
    this.idade = idade;
    this.cep = cep;
    this.estado = estado;
    this.cidade = cidade;
    this.bairro = bairro;
    this.logradouro = logradouro;
    this.telefone = telefone;
    this.score = score;
    this.role = role;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getNome() {
    return nome;
  }

  public int getIdade() {
    return idade;
  }

  public String getCep() {
    return cep;
  }

  public String getEstado() {
    return estado;
  }

  public String getCidade() {
    return cidade;
  }

  public String getBairro() {
    return bairro;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public String getTelefone() {
    return telefone;
  }

  public int getScore() {
    return score;
  }

  public PessoaRole getRole() {
    return role;
  }
}
