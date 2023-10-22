package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//interface para realizar as implementações de busca dos dados no nosso banco cliente.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //metodo para realizar a busca de um cliente especifico por sua conta.
    Optional <Cliente> findClienteByContaCliente(long contaCliente);

    //Metodo para realizar a busca de tos os cliente cadastrados em nossa tabela cliente.
    List<Cliente> findAll();

    /*
    metodo para podermos buscar a ultima conta cadastrada em nosso banco e dessa maneira podermos
    criar uma nova conta sem termos concorrencia e termos unicidade para a conta gerada
    */
    @Query("SELECT MAX(c.contaCliente) FROM cliente c")
    Long buscaUltimaContaCadastrada();
}
