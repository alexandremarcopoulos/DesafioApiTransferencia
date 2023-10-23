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
        // Cria um cliente de exemplo e salva ele no banco de dados
        Cliente cliente = new Cliente();
        cliente.setContaCliente(1001L);
        clienteRepository.save(cliente);

        // Use o método de repositório para encontrar o cliente pelo número da conta
        Optional<Cliente> foundCliente = clienteRepository.findClienteByContaCliente(1001L);

        // Verifique se o cliente foi encontrado corretamente
        assertTrue(foundCliente.isPresent());
        assertEquals(1001L, foundCliente.get().getContaCliente());
    }

    @Test
    public void testFindAll() {
        // Crie alguns clientes de exemplo e salve-os no banco de dados
        Cliente cliente1 = new Cliente();
        cliente1.setContaCliente(1001L);
        Cliente cliente2 = new Cliente();
        cliente2.setContaCliente(1002L);
        clienteRepository.saveAll(List.of(cliente1, cliente2));

        // Use o método de repositório para encontrar todos os clientes
        List<Cliente> allClientes = clienteRepository.findAll();

        // Verifique se todos os clientes foram encontrados corretamente
        assertEquals(2, allClientes.size());
        assertEquals(1001L, allClientes.get(0).getContaCliente());
        assertEquals(1002L, allClientes.get(1).getContaCliente());
    }

    @Test
    public void testBuscaUltimaContaCadastrada() {
        // Crie alguns clientes de exemplo com diferentes números de conta
        Cliente cliente1 = new Cliente();
        cliente1.setContaCliente(1001L);
        Cliente cliente2 = new Cliente();
        cliente2.setContaCliente(1002L);
        clienteRepository.saveAll(List.of(cliente1, cliente2));

        // Use o método de repositório para buscar a última conta cadastrada
        Long ultimaConta = clienteRepository.buscaUltimaContaCadastrada();

        // Verifique se a última conta foi encontrada corretamente
        assertEquals(1002L, ultimaConta);
    }
}
