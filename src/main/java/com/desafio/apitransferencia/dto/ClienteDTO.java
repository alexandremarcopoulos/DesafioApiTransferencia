package com.desafio.apitransferencia.dto;

import java.math.BigDecimal;

//Definição da record para que possamos ter um contrato inicial para a criacao de um cliente novo.
public record ClienteDTO(String nomeCliente, String documentoCliente, BigDecimal saldoCliente) {

}
