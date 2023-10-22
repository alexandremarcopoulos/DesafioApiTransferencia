package com.desafio.apitransferencia.domain.transacao;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
Classe para a implementação do banco de dados referente a transacao e com as anotações relacionadas
para a criacao de seu consntrutor, getters e setters, nome de entidade jpa, tabela h2 e a maneira de
gerar o seu id
*/
@Entity(name = "transacao")
@Table(name = "transacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {

    /*
    Instanciacao dos campos necessarios para a tabela transacao como idTransacao, clientePagador,
    clienteRecebedor, valorTransacao e por fim dataHoraTransacao.

    Temos também as anotacoes referentes ao id, a maneira que o id sera gerado e a transforção da variavel
    em unica.

    Também temos as anotações ManyToOne que permitem que muitas transações possa ser realizadas por um
    cliente e também as anutações para imput dos campos relacionados
    */

    //Id referente a conta que esta realizando o pagamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long idTransacao;

    private long contaCliente;

    @ManyToOne
    @JoinColumn(name = "cliente_pagador")
    private Cliente clientePagador;

    @ManyToOne
    @JoinColumn(name = "cliente_recebedor")
    private Cliente clienteRecebedor;

    private BigDecimal valorTransacao;

    private LocalDateTime dataHoraTransacao;

}
