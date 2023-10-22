package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    /*
    Chamada da classe para poder realizar a implementação dos metodos desta classe(ClienteService)
    com os metodos da classe ClienteRepository
    */
    @Autowired
    private ClienteRepository clienteRepositorio;

    //Metodo para realizar a busca de um cliente em especifico usando apenas a conta do cliente
    public Cliente buscaCLienteEspecifico(long contaCliente) throws Exception {
        return this.clienteRepositorio.findClienteByContaCliente(contaCliente).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    /*
    Metodo criado para que possamos cadastrar um novo cliente e também para que possamos implementar o numero
    de conta diferente e unico entre as contas cadastradas
     */
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

    //Metodo para salvar tanto o cadastro de um cliente como para realizar o salvamento de uma transacao
    public void salvaCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    //Metodo para trazer todos os clientes que estao cadastrados em nosso banco de dados
    public List<Cliente> buscarClientes() {
        return this.clienteRepositorio.findAll();
    }
}
