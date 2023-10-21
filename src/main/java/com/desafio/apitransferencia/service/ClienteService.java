package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepositorio;

    public Cliente buscaCLienteEspecifico(long numeroConta) throws Exception {
        return this.clienteRepositorio.findClienteByNumeroConta(numeroConta).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void salvaCliente(Cliente cliente) {
        this.clienteRepositorio.save(cliente);
    }

    public Cliente cadastraCliente(ClienteDTO clienteNaoCadastrado) {
        Cliente novoCliente = new Cliente(clienteNaoCadastrado);
        this.salvaCliente(novoCliente);
        return novoCliente;
    }

    public List<Cliente> buscarClientes() {
        return this.clienteRepositorio.findAll();
    }
}
