package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.Leilao;
import com.fatec.repository.LeilaoRepository;

@Singleton
public class LeilaoServiceImpl implements LeilaoService {

    @Inject
    private final LeilaoRepository leilaoRepository;

    public LeilaoServiceImpl(LeilaoRepository leilaoRepository) {
        this.leilaoRepository = leilaoRepository;
    }

    @Override
    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll();
    }

    @Override
    public Leilao salvar(Leilao leilao) {
        return leilaoRepository.save(leilao);
    }

    @Override
    public Optional<Leilao> buscarPorId(Long id) {
        return leilaoRepository.findById(id);
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
        }).orElseThrow(() -> new IllegalArgumentException("Leilão com ID " + id + " não encontrado"));
    }

    @Override
    public void deletar(Long id) {
        leilaoRepository.deleteById(id);
    }
}
