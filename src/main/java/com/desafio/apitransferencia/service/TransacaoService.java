package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.domain.usuario.Conta;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.repository.ContaRepository;
import com.desafio.apitransferencia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransacaoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransacaoRepository transacaoRepository;


    public void validaTransacao(Conta clienteTranferidor, BigDecimal valor) throws Exception {
        //Verifica se o cliente solicitante tem o saldo suficiente para realizar a transação

        if (clienteTranferidor.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Cliente não tem saldo para realizar a transação");
        } else if (clienteTranferidor.getSaldo().compareTo(valor) > 1000) {
            throw new Exception("Transação com valor superior a R$ 1000.00 Reais");
        }

    }

//    public void criaTransacao(TransacaoDTO transacao) throws Exception {
//        Conta clientePagador = this.clienteService.buscaCLienteEspecifico(transacao.numeroContaPagador());
//        Conta clienteRecebedor = this.clienteService.buscaCLienteEspecifico(transacao.numeroContaRecebedor());
//
//        validaTransacao(clientePagador, transacao.valorTransacao());
//
//        Transacao transacaoCliente = new Transacao();
//        transacaoCliente.setValorTransacao(transacao.valorTransacao());
//        transacaoCliente.setClientePagador(clientePagador);
//        transacaoCliente.setClienteRecebedor(clienteRecebedor);
//        transacaoCliente.setDataHoraTransacao(LocalDateTime.now());
//
//        clientePagador.setSaldo(clientePagador.getSaldo().subtract(transacao.valorTransacao()));
//        clienteRecebedor.setSaldo(clienteRecebedor.getSaldo().add(transacao.valorTransacao()));
//
//        this.transacaoRepository.save(transacaoCliente);
//        this.clienteService.cadastraClienteEConta(clientePagador);
//        this.clienteService.salvaConta(clienteRecebedor);
//    }
}
