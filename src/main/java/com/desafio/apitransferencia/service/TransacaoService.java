package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public void criaTransacao(TransacaoDTO transacao) throws Exception{
        Cliente clientePagador = this.clienteService.findClienteById(transacao.numeroContaPagador());
        Cliente clienteRecebedor = this.clienteService.findClienteById(transacao.numeroContaRecebedor());

        clienteService.validaTransacao(clientePagador, transacao.valorTransacao());

        Transacao transacaoCliente = new Transacao();
        transacaoCliente.setValorTransacao(transacao.valorTransacao());
        transacaoCliente.setClientePagador(clientePagador);
        transacaoCliente.setClienteRecebedor(clienteRecebedor);
        transacaoCliente.setDataHoraTransacao(LocalDateTime.now());

        clientePagador.setSaldoConta(clientePagador.getSaldoConta().subtract(transacao.valorTransacao()));
        clienteRecebedor.setSaldoConta(clienteRecebedor.getSaldoConta().add(transacao.valorTransacao()));

        this.transacaoRepository.save(transacaoCliente);
        this.clienteService.salvaCliente(clientePagador);
        this.clienteService.salvaCliente(clienteRecebedor);
    }
}
