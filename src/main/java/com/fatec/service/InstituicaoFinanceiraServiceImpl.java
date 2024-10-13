package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.InstituicaoFinanceira;
import com.fatec.repository.InstituicaoFinanceiraRepository;

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
        return repository.findById(id);
    }

    @Override
    public InstituicaoFinanceira salvar(InstituicaoFinanceira instituicaoFinanceira) {
        return repository.save(instituicaoFinanceira);
    }

    @Override
    public InstituicaoFinanceira atualizar(Long id, InstituicaoFinanceira instituicaoFinanceira) {
        Optional<InstituicaoFinanceira> existing = repository.findById(id);
        if (existing.isPresent()) {
            InstituicaoFinanceira updated = existing.get();
            updated.setNome(instituicaoFinanceira.getNome());
            updated.setCnpj(instituicaoFinanceira.getCnpj());
            updated.setEndereco(instituicaoFinanceira.getEndereco());
            updated.setTelefone(instituicaoFinanceira.getTelefone());
            return repository.update(updated);
        } else {
            return null;
        }
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}