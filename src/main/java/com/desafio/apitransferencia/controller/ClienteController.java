package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping

    public ResponseEntity<Cliente> cadastraCliente(ClienteDTO cliente) {
        Cliente novoCliente = clienteService.cadastraCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosClientes(){
        List<Cliente> clientes = this.clienteService.buscarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
