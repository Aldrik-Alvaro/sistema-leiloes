package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.DispositivoInformatica;
import com.fatec.repository.DispositivoRepository;

@Singleton
public class DispositivoServiceImpl implements DispositivoService {

    @Inject
    private final DispositivoRepository dispositivoRepository;

    public DispositivoServiceImpl(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    @Override
    public List<DispositivoInformatica> listarTodos() {
        return dispositivoRepository.findAll();
    }

    @Override
    public DispositivoInformatica salvar(DispositivoInformatica dispositivo) {
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

