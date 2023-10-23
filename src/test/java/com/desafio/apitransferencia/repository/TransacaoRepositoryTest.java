package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.transacao.Transacao;
import com.desafio.apitransferencia.domain.usuario.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class TransacaoRepositoryTest {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Test
    public void testBuscaTrasacaoPorContaCliente() {
        // Criando um cliente de exemplo e salvando no banco de dados
        Cliente cliente = new Cliente();
        cliente.setContaCliente(1001L);

        // Criacao de transacoes a seterem instanciadas no meu banco de dados
        Transacao transacao1 = new Transacao();
        transacao1.setContaCliente(1001L);
        transacao1.setValorTransacao(new BigDecimal(500));
        Transacao transacao2 = new Transacao();
        transacao2.setContaCliente(1001L);
        transacao2.setValorTransacao(new BigDecimal(50));
        transacaoRepository.saveAll(List.of(transacao1, transacao2));

        // Metodo de repositorio para encontrar as transações relacionadas ao cliente
        Optional<List<Transacao>> foundTransacoes = transacaoRepository.findTrasacaoByContaClienteOrderByDataHoraTransacaoDesc(1001L);

        // Transações foram encontradas corretamente
        assertTrue(foundTransacoes.isPresent());
        assertEquals(2, foundTransacoes.get().size());

        // Valor das transações encontradas
        assertEquals(new BigDecimal(500), foundTransacoes.get().get(0).getValorTransacao());
        assertEquals(new BigDecimal(50), foundTransacoes.get().get(1).getValorTransacao());
    }

}
