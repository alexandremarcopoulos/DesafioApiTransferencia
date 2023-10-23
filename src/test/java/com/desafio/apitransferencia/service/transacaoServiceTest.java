package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class transacaoServiceTest {
    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriaTransacao() throws Exception {
        TransacaoDTO transacaoDTO = new TransacaoDTO(new BigDecimal(1000),1000L, 1001L );

        Cliente clientePagador = new Cliente();
        clientePagador.setContaCliente(1001L);
        clientePagador.setSaldoCliente(new BigDecimal(1000));

        Cliente clienteRecebedor = new Cliente();
        clienteRecebedor.setContaCliente(1002L);
        clienteRecebedor.setSaldoCliente(new BigDecimal(1000));

        Transacao transacao = new Transacao();
        transacao.setValorTransacao(new BigDecimal(1000));
        transacao.setContaCliente(1001L);
        transacao.setClientePagador(clientePagador);
        transacao.setClienteRecebedor(clienteRecebedor);
        transacao.setDataHoraTransacao(LocalDateTime.now());

        when(clienteService.buscaCLienteEspecifico(1000L)).thenReturn(clientePagador);
        when(clienteService.buscaCLienteEspecifico(1001L)).thenReturn(clienteRecebedor);
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao result = transacaoService.criaTransacao(transacaoDTO);

        assertNotNull(result);
        assertEquals(new BigDecimal(1000), result.getValorTransacao());
        assertEquals(1001L, result.getContaCliente());
    }

    @Test
    public void testValidaTransacao() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setSaldoCliente(new BigDecimal(1000));

        assertDoesNotThrow(() -> transacaoService.validaTransacao(cliente, BigDecimal.valueOf(500.0)));
        assertThrows(Exception.class, () -> transacaoService.validaTransacao(cliente, BigDecimal.valueOf(1500.0)));
    }

    @Test
    public void testBuscaTransacaoCliente() throws Exception {
        long numeroContaPagador = 1001L;
        Transacao transacao = new Transacao();
        transacao.setContaCliente(numeroContaPagador);
        when(transacaoRepository.findTrasacaoByContaCliente(numeroContaPagador)).thenReturn(Optional.of(List.of(transacao)));

        List<Transacao> result = transacaoService.buscaTransacaoCliente(numeroContaPagador);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(numeroContaPagador, result.get(0).getContaCliente());
    }
}
