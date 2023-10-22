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

    private String documentoCliente;

    private String nomeCliente;

    @Column(unique = true)
    private long contaCliente;

    private BigDecimal saldoCliente;

    public Cliente(ClienteDTO clienteNaoCadastrado,Long ultimoNumeroConta) {
        this.documentoCliente = clienteNaoCadastrado.documentoCliente();
        this.nomeCliente = clienteNaoCadastrado.nomeCliente();
        this.saldoCliente = clienteNaoCadastrado.saldoCliente();
        this.contaCliente = ultimoNumeroConta;
    }

}
