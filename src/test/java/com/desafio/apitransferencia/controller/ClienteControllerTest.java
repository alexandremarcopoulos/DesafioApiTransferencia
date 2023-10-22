package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    private ClienteController clienteController;

    ClienteDTO clienteDTO = new ClienteDTO("Alexandre", "39367475802",new BigDecimal(1000));

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteController = new ClienteController(clienteService);
    }

    @Test
    public void testCadastraClienteEConta() {
        ClienteDTO clienteDTO = new ClienteDTO("Alexandre", "39367475802",new BigDecimal(1000));

        Cliente novoCliente = new Cliente();
        novoCliente.setNomeCliente(clienteDTO.nomeCliente());
        novoCliente.setDocumentoCliente(clienteDTO.documentoCliente());
        novoCliente.setSaldoCliente(clienteDTO.saldoCliente());

        Mockito.when(clienteService.cadastraCliente(clienteDTO)).thenReturn(novoCliente);

        ResponseEntity<Cliente> response = clienteController.cadastraClienteEConta(clienteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novoCliente, response.getBody());
    }

    @Test
    public void testBuscaClienteEspecifico() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Alexandre Marcopoulos Henrique");
        cliente.setContaCliente(cliente.getContaCliente());

        Mockito.when(clienteService.buscaCLienteEspecifico(cliente.getContaCliente())).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.buscaCLienteEspecifico(cliente);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testBuscarTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(clienteDTO, 1001L));
        clientes.add(new Cliente(clienteDTO, 1002L));

        Mockito.when(clienteService.buscarClientes()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = clienteController.buscarTodosClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }
}
