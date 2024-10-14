package com.fatec.service;

import java.util.List;
import java.util.Optional;

import com.fatec.entity.InstituicaoFinanceira;

public interface InstituicaoFinanceiraService {

    List<InstituicaoFinanceira> listarTodos();

    Optional<InstituicaoFinanceira> buscarPorId(Long id);

    InstituicaoFinanceira salvar(InstituicaoFinanceira instituicaoFinanceira);

    InstituicaoFinanceira atualizar(Long id, InstituicaoFinanceira instituicaoFinanceira);

    void deletar(Long id);
}
