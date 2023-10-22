package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//metodos para a chamada da rota HTTP controladora /transacao.
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    /*
    Chamada da classe Service para poder realizar a implementação dos metodos desta classe (TransacaoController)
    com os metodos da classe TransacaoService.
    */
    @Autowired
    private TransacaoService transacaoService;

    /*
    Metodo para a realizacao da trasancao entre duas contas seguindo os padrões da classe
    TransacaoDTO e após validar o padrão de envio e realizar a chamada para o metodo criaTransacao
    da classe TransacaoService
    */
    @PostMapping("/realizatransacao")
    public ResponseEntity<Transacao> realizaTransacao(@RequestBody TransacaoDTO transacaoDTO) throws Exception {
        Transacao novaTransacao = this.transacaoService.criaTransacao(transacaoDTO);

        return new ResponseEntity<>(novaTransacao, HttpStatus.CREATED);
    }

    /*
    Metodo para realizar a busca de todas as Transacoes de um cliente especifico usando em seu
    contrato apenas a conta do cliente que pode ser implementado como {"contaCliente": "1000"} e
    também realiza uma chamada a classe ClienteService pelo metodo buscaCLienteEspecifico.
    */
    @PostMapping("/buscatransacao")
    public  ResponseEntity<List<Transacao>> buscaTransacaoCliente (@RequestBody Transacao contaCliente) throws Exception{
        List<Transacao> buscaTransacao = this.transacaoService.buscaTransacaoCliente(contaCliente.getContaCliente());
        return new ResponseEntity<>(buscaTransacao,HttpStatus.OK);
    }

}
