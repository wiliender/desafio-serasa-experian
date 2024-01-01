package com.experian.serasa.rest.api.repositories;

import com.experian.serasa.rest.api.domain.pessoa.Pessoa;
import com.experian.serasa.rest.api.domain.pessoa.RegisterDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PessoaRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Test
    void findByLogin() {
    }

    @Test
    void findAll() {
    }
}