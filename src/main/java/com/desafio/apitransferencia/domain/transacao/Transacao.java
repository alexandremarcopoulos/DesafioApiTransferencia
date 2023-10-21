package com.desafio.apitransferencia.domain.transacao;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transacao")
@Table(name = "transacao")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long idTransacao;

    @ManyToOne
    @JoinColumn(name = "cliente_pagador")
    private Cliente clientePagador;

    @ManyToOne
    @JoinColumn(name = "cliente_recebedor")
    private Cliente clienteRecebedor;

    private BigDecimal valorTransacao;

    private LocalDateTime dataHoraTransacao;
}
