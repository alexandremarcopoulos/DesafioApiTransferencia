package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//interface para realizar as implementações de busca dos dados no nosso banco transacao.
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    /*
    Metodo para realizar a busca no banco de transacoes e trazer todas as transacoes realizadas por um
    cliente.
    */
    Optional <List<Transacao>> findTrasacaoByContaCliente(long contaCliente);
}
