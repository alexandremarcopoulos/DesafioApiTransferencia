package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.TransacaoDTO;
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

    public void validaTransacao(Cliente clienteTranferidor, BigDecimal valor) throws Exception {
        //Verifica se o cliente solicitante tem o saldo suficiente para realizar a transação

        if (clienteTranferidor.getConta().getSaldo().compareTo(valor) < 0) {
            throw new Exception("Cliente não tem saldo para realizar a transação");
        } else if (clienteTranferidor.getConta().getSaldo().compareTo(valor) > 1000) {
            throw new Exception("Transação com valor superior a R$ 1000.00 Reais");
        }

    }

    public void criaTransacao(TransacaoDTO transacao) throws Exception {
        Cliente clientePagador = this.clienteService.buscaCLienteEspecifico(transacao.numeroContaPagador());
        Cliente clienteRecebedor = this.clienteService.buscaCLienteEspecifico(transacao.numeroContaRecebedor());

        validaTransacao(clientePagador, transacao.valorTransacao());

        Transacao transacaoCliente = new Transacao();
        transacaoCliente.setValorTransacao(transacao.valorTransacao());
        transacaoCliente.setClientePagador(clientePagador);
        transacaoCliente.setClienteRecebedor(clienteRecebedor);
        transacaoCliente.setDataHoraTransacao(LocalDateTime.now());

        clientePagador.getConta().setSaldo(clientePagador.getConta().getSaldo().subtract(transacao.valorTransacao()));
        clienteRecebedor.getConta().setSaldo(clienteRecebedor.getConta().getSaldo().add(transacao.valorTransacao()));

        this.transacaoRepository.save(transacaoCliente);
        this.clienteService.salvaCliente(clientePagador);
        this.clienteService.salvaCliente(clienteRecebedor);
    }
}
