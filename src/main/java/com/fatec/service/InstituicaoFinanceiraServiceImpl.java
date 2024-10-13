package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.InstituicaoFinanceira;
import com.fatec.repository.InstituicaoFinanceiraRepository;
import com.fatec.exception.ResourceNotFoundException;
import com.fatec.exception.InvalidOperationException;

@Singleton
public class InstituicaoFinanceiraServiceImpl implements InstituicaoFinanceiraService {

    @Inject
    private final InstituicaoFinanceiraRepository repository;

    public InstituicaoFinanceiraServiceImpl(InstituicaoFinanceiraRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InstituicaoFinanceira> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<InstituicaoFinanceira> buscarPorId(Long id) {
        return repository.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Instituição Financeira com ID " + id + " não encontrada.");
                });
    }

    @Override
    public InstituicaoFinanceira salvar(InstituicaoFinanceira instituicaoFinanceira) {
        return repository.save(instituicaoFinanceira);
    }

    @Override
    public InstituicaoFinanceira atualizar(Long id, InstituicaoFinanceira instituicaoFinanceira) {
        return repository.findById(id).map(existing -> {
            existing.setNome(instituicaoFinanceira.getNome());
            existing.setCnpj(instituicaoFinanceira.getCnpj());
            existing.setEndereco(instituicaoFinanceira.getEndereco());
            existing.setTelefone(instituicaoFinanceira.getTelefone());
            return repository.update(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Instituição Financeira com ID " + id + " não encontrada."));
    }

    @Override
    public void deletar(Long id) {
        Optional<InstituicaoFinanceira> instituicao = repository.findById(id);

        if (instituicao.isEmpty()) {
            throw new ResourceNotFoundException("Instituição Financeira com ID " + id + " não encontrada.");
        }

        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new InvalidOperationException("Erro ao tentar remover a Instituição Financeira com ID " + id + ".");
        }
    }
}