package com.fatec.service;

import java.util.List;
import java.util.Optional;

import com.fatec.entity.Leilao;

public interface LeilaoService {

    Leilao salvar(Leilao leilao);

    Optional<Leilao> buscarPorId(Long id);

    List<Leilao> listarTodos();

    Leilao atualizar(Long id, Leilao leilaoAtualizado);

    void deletar(Long id);
}

