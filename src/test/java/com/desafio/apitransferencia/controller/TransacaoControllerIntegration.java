package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransacaoControllerIntegration {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    public void testCadastraClienteEConta() {
        ClienteDTO clienteDTO1 = new ClienteDTO("Alexandre", "39367475802", new BigDecimal(1000));
        ClienteDTO clienteDTO2 = new ClienteDTO("Alexandre", "39367475802", new BigDecimal(1000));

        ResponseEntity<Cliente> response1 = restTemplate.postForEntity(
                "http://localhost:" + port + "/cliente/cadastracliente",
                clienteDTO1, Cliente.class);
        ResponseEntity<Cliente> response2 = restTemplate.postForEntity(
                "http://localhost:" + port + "/cliente/cadastracliente",
                clienteDTO1, Cliente.class);

        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertNotNull(response1.getBody());
        assertNotNull(response2.getBody());
    }
    @Test
    public void testRealizaTransacao() throws Exception {
        testCadastraClienteEConta();
        // Crie um objeto TransacaoDTO com os dados da transação
        TransacaoDTO transacaoDTO = new TransacaoDTO(new BigDecimal(1000),1000L, 1001L);

        ResponseEntity<Transacao> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/transacao/realizatransacao",
                transacaoDTO,
                Transacao.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testBuscaTransacaoCliente() {
        Transacao transacao = new Transacao();
        transacao.setContaCliente(1000);

        ResponseEntity<List<Transacao>> response = restTemplate.exchange(
                "http://localhost:" + port + "/transacao/buscatransacao",
                HttpMethod.POST,
                new HttpEntity<>(transacao),
                new ParameterizedTypeReference<List<Transacao>>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
