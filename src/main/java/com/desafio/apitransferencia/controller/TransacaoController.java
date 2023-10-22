package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Transacao> realizaTransacao(@RequestBody TransacaoDTO transacaoDTO) throws Exception {
        Transacao novaTransacao = this.transacaoService.criaTransacao(transacaoDTO);

        return new ResponseEntity<>(novaTransacao, HttpStatus.CREATED);
    }
}
