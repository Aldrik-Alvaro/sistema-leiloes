package com.fatec.service;

import java.util.List;
import java.util.Optional;

import com.fatec.entity.Veiculo;

public interface VeiculoService {

    List<Veiculo> listarTodos();

    Veiculo salvar(Veiculo veiculo);

    Optional<Veiculo> buscarPorId(Long id);

    Veiculo atualizar(Long id, Veiculo veiculoAtualizado);

    void deletar(Long id);

    void desassociarVeiculoLeilao(Long dispositivoId, Long leilaoId, Long novoLeilaoId);
}

