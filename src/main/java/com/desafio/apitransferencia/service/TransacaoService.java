package com.desafio.apitransferencia.service;

import com.desafio.apitransferencia.domain.usuario.Cliente;
import com.desafio.apitransferencia.dto.TransacaoDTO;
import com.desafio.apitransferencia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public void criaTransacao(TransacaoDTO transacao) throws Exception{
        Cliente cliente = this.clienteService.findClienteById(transacao.numeroContaPagador());
    }
}
