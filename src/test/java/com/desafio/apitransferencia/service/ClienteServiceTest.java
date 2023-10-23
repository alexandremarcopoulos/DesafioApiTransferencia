package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.ClienteDTO;
import com.desafio.apitransferencia.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscaClienteEspecifico() throws Exception {
        // Simula o comportamento do ClienteRepository
        long contaCliente = 1234L;
        Cliente cliente = new Cliente(new ClienteDTO("Alexandre Marcopoulos Henrique",
                "39367475802", new BigDecimal(1000)), contaCliente);
        when(clienteRepository.findClienteByContaCliente(contaCliente)).thenReturn(Optional.of(cliente));

        // Chama o metodo de serviço
        Cliente result = clienteService.buscaCLienteEspecifico(contaCliente);

        // Verifica se o resultado é o esperado
        assertEquals(cliente, result);
    }

    @Test
    public void testBuscaClienteEspecificoClienteNaoEncontrado() {
        // Simula o ClienteRepository retornando um Optional vazio
        long contaCliente = 5678L;
        when(clienteRepository.findClienteByContaCliente(contaCliente)).thenReturn(Optional.empty());

        // Chama o metodo de serviço e espera uma exceção
        Exception exception = assertThrows(Exception.class, () -> {
            clienteService.buscaCLienteEspecifico(contaCliente);
        });

        // Verifica se a mensagem da exceção é a esperada
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    public void testCadastraCliente() {
        // Simula o comportamento do ClienteRepository
        ClienteDTO clienteDTO = new ClienteDTO("Alexandre Marcopoulos Henrique",
                "39367475802", new BigDecimal(1000));
        when(clienteRepository.buscaUltimaContaCadastrada()).thenReturn(1000L);

        // Chama o metodo de serviço
        Cliente result = clienteService.cadastraCliente(clienteDTO);

        // Verifica se o número de conta foi incrementado e o cliente foi salvo
        assertEquals(1001L, result.getContaCliente());
        verify(clienteRepository).save(result);
    }

    @Test
    public void testCadastraPrimeiroCliente() {
        // Simula o comportamento do ClienteRepository para o primeiro cliente
        ClienteDTO clienteDTO = new ClienteDTO("Alexandre Marcopoulos Henrique",
                "39367475802", new BigDecimal(1000));
        when(clienteRepository.buscaUltimaContaCadastrada()).thenReturn(null);

        // Chama o metodo de serviço
        Cliente result = clienteService.cadastraCliente(clienteDTO);

        // Verifica se o número de conta é 1000 e o cliente foi salvo
        assertEquals(1000L, result.getContaCliente());
        verify(clienteRepository).save(result);
    }

    @Test
    public void testBuscarClientes() {
        // Simula o comportamento do ClienteRepository
        List<Cliente> clientes = new ArrayList<>();
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Chama o metodo de serviço
        List<Cliente> result = clienteService.buscarClientes();

        // Verifica se a lista de clientes retornada é a mesma que foi simulada
        assertEquals(clientes, result);
    }
}
