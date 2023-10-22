package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional <Cliente> findClienteByContaCliente(long contaCliente);

    List<Cliente> findAll();
    @Query("SELECT MAX(c.contaCliente) FROM cliente c")
    Long buscaUltimaContaCadastrada();
}
