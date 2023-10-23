# Read.me

## Para que a aplicação possa ser executada em seu computador será necessario ter:

- JDK 17
- Manven 3.1.5

## Temos os seguintes endpoints:

Cadastro de um cliente (Metodo Post)

- /cliente/cadastracliente

```json
{
	"nomeCliente" : "Alexandre Marcopoulos Henrique",
	"documentoCliente" : "39367475802",
	"saldoCliente" : 1500.00
}
```

Retornar todos os clientes cadastrados (Metodo Get)

- /cliente

Retornar conta especifica (Metodo Post)

- cliente/buscacliente

```json
{
	"contaCliente": adicionarContaGeradaPelaApi
}
```

Retornar uma transacao entre duas contas (Metodo Post)

- transacao/realizatransacao

```json
{
	"valorTransacao": 100.00,
	"numeroContaPagador": 1000,
	"numeroContaRecebedor":1002
}
```

Retornar transacoes realizadas por um cliente determinado (metodo Post)

- transacao/buscatransacao

```json
{
	"contaCliente": adicionarContaGeradaPelaApi
}
```
