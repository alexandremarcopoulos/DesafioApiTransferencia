package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional <Cliente> findClienteByNumeroConta(int numeroConta);

    List<Cliente> findAll();
}
