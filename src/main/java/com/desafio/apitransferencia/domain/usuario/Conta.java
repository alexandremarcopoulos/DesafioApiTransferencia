package com.desafio.apitransferencia.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Conta")
@Table(name = "Conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long numeroConta;

    @OneToOne(mappedBy = "conta")
    private Cliente cliente;

    private BigDecimal saldo;

}
