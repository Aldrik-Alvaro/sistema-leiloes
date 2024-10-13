package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.DispositivoInformatica;
import com.fatec.repository.DispositivoRepository;
import com.fatec.repository.LeilaoRepository;

@Singleton
public class DispositivoServiceImpl implements DispositivoService {

    @Inject
    private final DispositivoRepository dispositivoRepository;
    private final LeilaoRepository leilaoRepository;

    public DispositivoServiceImpl(DispositivoRepository dispositivoRepository, LeilaoRepository leilaoRepository) {
        this.dispositivoRepository = dispositivoRepository;
        this.leilaoRepository = leilaoRepository;
    }

    @Override
    public List<DispositivoInformatica> listarTodos() {
        return dispositivoRepository.findAll();
    }

    @Override
    public DispositivoInformatica salvar(DispositivoInformatica dispositivo) {
        if (dispositivo.getLeilao() == null || leilaoRepository.findById(dispositivo.getLeilao().getId()).isEmpty()) {
            throw new IllegalArgumentException("Leilão não encontrado ou inválido.");
        }

        // Se o leilão for válido, salvar o dispositivo
        return dispositivoRepository.save(dispositivo);
    }

    @Override
    public Optional<DispositivoInformatica> buscarPorId(Long id) {
        return dispositivoRepository.findById(id);
    }

    @Override
    public DispositivoInformatica atualizar(Long id, DispositivoInformatica dispositivoAtualizado) {
        dispositivoAtualizado.setId(id);
        return dispositivoRepository.update(dispositivoAtualizado);
    }

    @Override
    public void remover(Long id) {
        dispositivoRepository.deleteById(id);
    }
}

