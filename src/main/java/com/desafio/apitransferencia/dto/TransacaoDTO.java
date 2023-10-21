package com.desafio.apitransferencia.dto;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal valorTransacao, int numeroContaPagador, int numeroContaRecebedor) {
}
