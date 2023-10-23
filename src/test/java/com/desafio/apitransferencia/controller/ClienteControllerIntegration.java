package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIntegration {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCadastraClienteEConta() {
        ClienteDTO clienteDTO = new ClienteDTO("Alexandre", "39367475802", new BigDecimal(1000));

        ResponseEntity<Cliente> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/cliente/cadastracliente",
                clienteDTO, Cliente.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    /*
    Este teste ainda não esta funcionando por conta da movimentacao do objeto cliente para o envio
    da requisicao http
    */
//    @Test
//    public void testBuscaClienteEspecifico() {
//        //cliente existente no banco de dados
//        testCadastraClienteEConta();
//        long contaCliente = 1000;
//
//        ResponseEntity<Cliente> response = restTemplate.postForEntity(
//                "http://localhost:" + port + "/cliente/buscacliente",
//                new HttpEntity<>(contaCliente),
//                new ParameterizedTypeReference Cliente);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody());
//    }

    @Test
    public void testBuscarTodosClientes() {
        //clientes existentes no banco de dados

        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/cliente", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
