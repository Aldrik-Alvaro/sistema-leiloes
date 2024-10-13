package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.DispositivoInformatica;
import com.fatec.entity.Leilao;
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

    @Override
    public void desassociarDispositivoDeLeilao(Long itemId, Long leilaoId, Long novoLeilaoId) {
        // Verifica se o item existe e está associado ao leilão atual
        DispositivoInformatica dispositivo = dispositivoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado"));

        Leilao leilaoAtual = leilaoRepository.findById(leilaoId)
                .orElseThrow(() -> new IllegalArgumentException("Leilão atual não encontrado"));

        // Verifica se o dispositivo está associado ao leilão atual
        if (!dispositivo.getLeilao().getId().equals(leilaoId)) {
            throw new IllegalArgumentException("O dispositivo não está associado ao leilão atual.");
        }

        // Verifica se o dispositivo foi vendido
        //if (dispositivoFoiVendido(dispositivo)) {
        //    throw new IllegalStateException("Não é possível desassociar um dispositivo que já foi vendido.");
        //}

        // Verifica se o novo leilão é válido
        Leilao novoLeilao = leilaoRepository.findById(novoLeilaoId)
                .orElseThrow(() -> new IllegalArgumentException("Novo leilão não encontrado."));

        // Associa o dispositivo ao novo leilão
        dispositivo.setLeilao(novoLeilao);
        dispositivoRepository.update(dispositivo);
    }

    // Método para verificar se o dispositivo foi vendido
    //private boolean dispositivoFoiVendido(DispositivoInformatica dispositivo) {
    //    // Implementar lógica para verificar se o dispositivo foi vendido (ex: verificar se recebeu lances)
    //    // Por exemplo, retornar true se houver lances associados ao dispositivo
    //    return false; // Exemplo simplificado
    //}

}
