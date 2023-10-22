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

    public Cliente buscaCLienteEspecifico(Long numeroConta) throws Exception {
        return this.clienteRepositorio.findClienteByNumeroConta(numeroConta).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public Cliente cadastraCliente(ClienteDTO clienteDTO) {
        Long ultimoNumeroConta = clienteRepositorio.buscaUltimaContaCadastrada();

        // Verifica se é o primeiro cliente
        if (ultimoNumeroConta == null) {
            ultimoNumeroConta = 1000L;
        } else {
            // Incrementa o número de conta para o próximo cliente
            ultimoNumeroConta++;
        }
        Cliente novoCliente = new Cliente(clienteDTO, ultimoNumeroConta);
        salvaCliente(novoCliente);
        return novoCliente;
    }

    public void salvaCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }
    public List<Cliente> buscarClientes() {
        return this.clienteRepositorio.findAll();
    }
}
