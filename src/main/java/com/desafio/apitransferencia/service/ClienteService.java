package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.repository.ClienteRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepositorio;

    public void validaTransacao(Cliente clienteTranferidor, BigDecimal valor) throws Exception {
        //Verifica se o cliente solicitante tem o saldo suficiente para realizar a transação

        if (clienteTranferidor.getSaldoConta().compareTo(valor) < 0) {
            throw new Exception("Cliente não tem saldo para realizar a transação");
        } else if (clienteTranferidor.getSaldoConta().compareTo(valor) > 1000) {
            throw new Exception("Transação com valor superior a R$ 1000.00 Reais");
        }

    }
    public Cliente findClienteById (int numeroConta) throws Exception {
        return this.clienteRepositorio.findClienteByNumeroConta(numeroConta).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void salvaCliente(Cliente cliente){
        this.clienteRepositorio.save(cliente);
    }

    public Cliente cadastraCliente(ClienteDTO clienteNaoCadastrado) {
        Cliente novoCliente = new Cliente(clienteNaoCadastrado);
        this.salvaCliente(novoCliente);
        return novoCliente;
    }
}
