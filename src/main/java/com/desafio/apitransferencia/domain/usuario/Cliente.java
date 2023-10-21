package com.desafio.apitransferencia.domain.usuario;

import com.desafio.apitransferencia.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    private int documentoCliente;

    private String nomeCliente;

    @Column(unique = true)
    private int numeroConta;

    private BigDecimal saldoConta;

    public Cliente(ClienteDTO clienteNaoCadastrado) {
        this.documentoCliente = clienteNaoCadastrado.documentoCLiente();
        this.nomeCliente = clienteNaoCadastrado.nomeCliente();
        this.saldoConta = clienteNaoCadastrado.saldoCliente();
    }
}
