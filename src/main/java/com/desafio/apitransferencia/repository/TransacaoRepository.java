package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
