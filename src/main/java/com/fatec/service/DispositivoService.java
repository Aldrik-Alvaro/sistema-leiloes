package com.fatec.service;

import java.util.List;
import java.util.Optional;

import com.fatec.entity.DispositivoInformatica;

public interface DispositivoService {

    List<DispositivoInformatica> listarTodos();

    DispositivoInformatica salvar(DispositivoInformatica dispositivo);

    Optional<DispositivoInformatica> buscarPorId(Long id);

    DispositivoInformatica atualizar(Long id, DispositivoInformatica dispositivoAtualizado);

    void deletar(Long id);

    void desassociarDispositivoDeLeilao(Long dispositivoId, Long leilaoId, Long novoLeilaoId);
}


