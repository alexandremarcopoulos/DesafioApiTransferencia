package com.desafio.apitransferencia.controller;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {
    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRealizaTransacao() throws Exception {
        TransacaoDTO transacaoDTO = new TransacaoDTO(new BigDecimal(200),1000L,1001L);
        // Defina aqui os campos necessários no transacaoDTO.

        Transacao transacao = new Transacao();
        when(transacaoService.criaTransacao(transacaoDTO)).thenReturn(transacao);

        ResponseEntity<Transacao> response = transacaoController.realizaTransacao(transacaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transacao, response.getBody());
    }

    @Test
    public void testBuscaTransacaoCliente() throws Exception {
        Transacao transacao = new Transacao();
        transacao.setContaCliente(1000L); // Defina a conta do cliente desejada.

        List<Transacao> transacoes = new ArrayList<>();
        // Adicione transações à lista transacoes conforme necessário.

        when(transacaoService.buscaTransacaoCliente(1000L)).thenReturn(transacoes);

        ResponseEntity<List<Transacao>> response = transacaoController.buscaTransacaoCliente(transacao);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transacoes, response.getBody());
    }
}
