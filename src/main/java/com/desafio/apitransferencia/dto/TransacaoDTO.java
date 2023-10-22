package com.desafio.apitransferencia.dto;

import com.desafio.apitransferencia.domain.usuario.Cliente;

import java.math.BigDecimal;

//Definição da record para que possamos ter um contrato inicial para a transacao de um cliente e outro.
public record TransacaoDTO(BigDecimal valorTransacao, long numeroContaPagador, long numeroContaRecebedor) {
}
