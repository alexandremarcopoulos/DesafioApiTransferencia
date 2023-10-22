package com.desafio.apitransferencia.domain.usuario;

import com.desafio.apitransferencia.dto.ContaDTO;
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
    private Long numeroConta;

    //@OneToOne(mappedBy = "conta")
    //private Cliente cliente;

    private BigDecimal saldo;

    public Conta(ContaDTO contaNaoCadastrada) {

    }
}
