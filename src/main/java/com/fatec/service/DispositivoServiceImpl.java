package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.DispositivoInformatica;
import com.fatec.repository.DispositivoRepository;
import com.fatec.repository.LeilaoRepository;
import com.fatec.exception.ResourceNotFoundException;
import com.fatec.exception.InvalidOperationException;

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
            throw new ResourceNotFoundException("Leilão não encontrado ou inválido.");
        }

        return dispositivoRepository.save(dispositivo);
    }

    @Override
    public Optional<DispositivoInformatica> buscarPorId(Long id) {
        return dispositivoRepository.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Dispositivo com ID " + id + " não encontrado.");
                });
    }

    @Override
    public DispositivoInformatica atualizar(Long id, DispositivoInformatica dispositivoAtualizado) {
        Optional<DispositivoInformatica> dispositivoExistente = dispositivoRepository.findById(id);

        if (dispositivoExistente.isEmpty()) {
            throw new ResourceNotFoundException("Dispositivo com ID " + id + " não encontrado.");
        }

        dispositivoAtualizado.setId(id);
        return dispositivoRepository.update(dispositivoAtualizado);
    }

    @Override
    public void deletar(Long id) {
        Optional<DispositivoInformatica> dispositivo = dispositivoRepository.findById(id);

        if (dispositivo.isEmpty()) {
            throw new ResourceNotFoundException("Dispositivo com ID " + id + " não encontrado.");
        }

        try {
            dispositivoRepository.deleteById(id);
        } catch (Exception e) {
            throw new InvalidOperationException("Erro ao tentar remover o dispositivo com ID " + id + ".");
        }
    }
}
