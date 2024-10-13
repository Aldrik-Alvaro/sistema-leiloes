package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.InstituicaoFinanceira;
import com.fatec.entity.Leilao;
import com.fatec.repository.LeilaoRepository;
import com.fatec.repository.InstituicaoFinanceiraRepository;
import com.fatec.exception.ResourceNotFoundException;
import com.fatec.exception.InvalidOperationException;

@Singleton
public class LeilaoServiceImpl implements LeilaoService {

    @Inject
    private final LeilaoRepository leilaoRepository;

    private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    public LeilaoServiceImpl(LeilaoRepository leilaoRepository, InstituicaoFinanceiraRepository instituicaoFinanceiraRepository) {
        this.leilaoRepository = leilaoRepository;
        this.instituicaoFinanceiraRepository = instituicaoFinanceiraRepository;
    }

    @Override
    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll();
    }

    @Override
    public Leilao salvar(Leilao leilao) {

        if (leilao.getInstituicoesFinanceiras() == null || leilao.getInstituicoesFinanceiras().isEmpty()) {
            throw new InvalidOperationException("Pelo menos uma instituição financeira deve ser associada ao leilão.");
        }

        for (InstituicaoFinanceira instituicao : leilao.getInstituicoesFinanceiras()) {
            if (instituicaoFinanceiraRepository.findById(instituicao.getId()).isEmpty()) {
                throw new InvalidOperationException("Instituição financeira não encontrada.");
            }
        }

        if (leilao.getDataOcorrencia().isBefore(LocalDate.now())) {
            throw new InvalidOperationException("Data de ocorrência do leilão não pode ser no passado.");
        }

        return leilaoRepository.save(leilao);
    }

    @Override
    public Optional<Leilao> buscarPorId(Long id) {
        return leilaoRepository.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Leilão com ID " + id + " não encontrado.");
                });
    }

    @Override
    public Leilao atualizar(Long id, Leilao leilaoAtualizado) {
        return leilaoRepository.findById(id).map(leilaoExistente -> {
            leilaoExistente.setDescricao(leilaoAtualizado.getDescricao());
            leilaoExistente.setDataOcorrencia(leilaoAtualizado.getDataOcorrencia());
            leilaoExistente.setDataVisitacao(leilaoAtualizado.getDataVisitacao());
            leilaoExistente.setEndereco(leilaoAtualizado.getEndereco());
            leilaoExistente.setCidade(leilaoAtualizado.getCidade());
            leilaoExistente.setEstado(leilaoAtualizado.getEstado());

            return leilaoRepository.update(leilaoExistente);
        }).orElseThrow(() -> new ResourceNotFoundException("Leilão com ID " + id + " não encontrado."));
    }

    @Override
    public void deletar(Long id) {
        Optional<Leilao> leilao = leilaoRepository.findById(id);

        if (leilao.isEmpty()) {
            throw new ResourceNotFoundException("Leilão com ID " + id + " não encontrado.");
        }

        try {
            leilaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new InvalidOperationException("Erro ao tentar remover o leilão com ID " + id + ".");
        }
    }
}