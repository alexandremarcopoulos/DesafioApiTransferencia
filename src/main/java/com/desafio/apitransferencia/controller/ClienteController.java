package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.repository.ClienteRepository;
import com.desafio.apitransferencia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//metodos para a chamada da rota HTTP controladora /cliente
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    /*
    Chamada da classe para poder realizar a implementação dos metodos desta classe(ClienteController)
    com os metodos da classe ClienteService
    */
    @Autowired
    private ClienteService clienteService;

    /*
    Metodo para cadastro do cliente onnde recebe como entrada o padrão de envio definido na classe
    ClienteDTO e após validar o padrão de envio realizar a chamada para o metodo cadastraCliente
    da classe ClienteService
    */
    @PostMapping("/cadastracliente")
    public ResponseEntity<Cliente> cadastraClienteEConta(@RequestBody ClienteDTO clienteDTO) {
        Cliente novoCliente = clienteService.cadastraCliente(clienteDTO);

        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    /*
    Metodo para realizar a busca de um cliente especifico usando em seu contrato apenas a conta
    do cliente que pode ser implementado como {"contaCliente": "1000"} e também realiza uma chamada
    a classe ClienteService pelo metodo buscaCLienteEspecifico.
    */
    @PostMapping("/buscacliente")
    public ResponseEntity<Cliente> buscaCLienteEspecifico(@RequestBody Cliente contaCliente) throws Exception {
        Cliente novoCliente = clienteService.buscaCLienteEspecifico(contaCliente.getContaCliente());

        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    /*
    Metodo para realizar a busca de todos os clientes já cadastrados em nosso banco de dados utilizando
    apenas de um metodo GET para que seja realizada a busca.
    */
    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosClientes() {
        List<Cliente> clientes = this.clienteService.buscarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
