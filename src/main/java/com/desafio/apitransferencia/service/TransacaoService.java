package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {
    /*
    Chamada da classe para poder realizar a implementação dos metodos desta classe(TransacaoService)
    com os metodos da classe ClienteService e TransacaoReposytory
    */
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    //Metodo para realizar a busca de todas as transacoes de um cliente especifico
    public List<Transacao> buscaTransacaoCliente(long numeroContaPagador) throws Exception {
        return this.transacaoRepository.findTrasacaoByContaClienteOrderByDataHoraTransacaoDesc(numeroContaPagador).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    /*metodo para validar a transacao que esta sendo executada, verificando saldo de conta e limite a ser
    transferido
     */
    public void validaTransacao(Cliente clienteTranferidor, BigDecimal valor) throws Exception {

        if (clienteTranferidor.getSaldoCliente().compareTo(valor) < 0) {
            throw new Exception("Cliente não tem saldo para realizar a transação");
        } else if (clienteTranferidor.getSaldoCliente().compareTo(valor) > 1000) {
            throw new Exception("Transação com valor superior a R$ 1000.00 Reais");
        }
    }

    /*
    Classe criada para realizar uma transacao utilizando o contrato TransacaoDTO onde é passado o valor
    dessa transacao, o cliente que esta efetuando o pagamento e o cliente que esta efetuando o recebimento
    */
    public Transacao criaTransacao(TransacaoDTO transacao) throws Exception {

        Cliente clientePagador = this.clienteService.buscaCLienteEspecifico(transacao.numeroContaPagador());
        Cliente clienteRecebedor = this.clienteService.buscaCLienteEspecifico(transacao.numeroContaRecebedor());

        validaTransacao(clientePagador, transacao.valorTransacao());

        Transacao transacaoCliente = new Transacao();
        transacaoCliente.setValorTransacao(transacao.valorTransacao());
        transacaoCliente.setContaCliente(clientePagador.getContaCliente());
        transacaoCliente.setClientePagador(clientePagador);
        transacaoCliente.setClienteRecebedor(clienteRecebedor);
        transacaoCliente.setDataHoraTransacao(LocalDateTime.now());

        clientePagador.setSaldoCliente(clientePagador.getSaldoCliente().subtract(transacao.valorTransacao()));
        clienteRecebedor.setSaldoCliente(clienteRecebedor.getSaldoCliente().add(transacao.valorTransacao()));

        this.transacaoRepository.save(transacaoCliente);
        this.clienteService.salvaCliente(clientePagador);
        this.clienteService.salvaCliente(clienteRecebedor);

        return transacaoCliente;
    }
}
