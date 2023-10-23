package com.desafio.apitransferencia.repository;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testFindClienteByContaCliente() {
        Cliente cliente = new Cliente();
        cliente.setContaCliente(1001L);
        clienteRepository.save(cliente);

        Optional<Cliente> foundCliente = clienteRepository.findClienteByContaCliente(1001L);

        assertTrue(foundCliente.isPresent());
        assertEquals(1001L, foundCliente.get().getContaCliente());
    }

    @Test
    public void testFindAll() {
        // Criando alguns clientes
        Cliente cliente1 = new Cliente();
        cliente1.setContaCliente(1001L);
        Cliente cliente2 = new Cliente();
        cliente2.setContaCliente(1002L);
        clienteRepository.saveAll(List.of(cliente1, cliente2));

        // Metodo para encontrar esses clientes
        List<Cliente> allClientes = clienteRepository.findAll();

        // Verifique se todos os clientes foram encontrados corretamente
        assertEquals(2, allClientes.size());
        assertEquals(1001L, allClientes.get(0).getContaCliente());
        assertEquals(1002L, allClientes.get(1).getContaCliente());
    }

    @Test
    public void testBuscaUltimaContaCadastrada() {
        // Criando clientes com alguns numeros de conta
        Cliente cliente1 = new Cliente();
        cliente1.setContaCliente(1001L);
        Cliente cliente2 = new Cliente();
        cliente2.setContaCliente(1002L);
        clienteRepository.saveAll(List.of(cliente1, cliente2));

        // Buscar a última conta cadastrada
        Long ultimaConta = clienteRepository.buscaUltimaContaCadastrada();

        // Verificar se a ultima conta foi encontrada corretamente
        assertEquals(1002L, ultimaConta);
    }
}
