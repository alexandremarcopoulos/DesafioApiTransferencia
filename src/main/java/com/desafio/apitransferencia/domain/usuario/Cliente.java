package com.desafio.apitransferencia.domain.usuario;

import com.desafio.apitransferencia.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/*
Classe para a implementação do banco de dados referente ao cliente e com as anotações relacionadas
para a criacao de seu consntrutor, getters e setters, nome de entidade jpa, tabela h2 e a maneira de
gerar o seu id
*/
@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    /*
    Instanciacao dos campos necessarios para a tabela CLiente como id, documentoCliente, nomeCliente,
    contaCliente e por fim saldoCliente.

    Temos também as anotacoes referentes ao id, a maneira que o id sera gerado automaticamente e a
    transforção da variavel em unica, sendo assim não podendo ter dois valores iguais no banco de dados.

    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    private String documentoCliente;

    private String nomeCliente;

    @Column(unique = true)
    private long contaCliente;

    private BigDecimal saldoCliente;

    /*
    Implementacao de um construtor padrao para podermos agregar valores a tabela vindos da classe CLienteDTO
    e também a implementacao do numero da ultima conta gerado.
    */
    public Cliente(ClienteDTO clienteNaoCadastrado,Long ultimoNumeroConta) {
        this.documentoCliente = clienteNaoCadastrado.documentoCliente();
        this.nomeCliente = clienteNaoCadastrado.nomeCliente();
        this.saldoCliente = clienteNaoCadastrado.saldoCliente();
        this.contaCliente = ultimoNumeroConta;
    }

}
