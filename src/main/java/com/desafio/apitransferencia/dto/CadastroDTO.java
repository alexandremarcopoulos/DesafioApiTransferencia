package com.desafio.apitransferencia.dto;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.domain.usuario.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CadastroDTO {
    private Cliente cliente;
    private Conta conta;
}
