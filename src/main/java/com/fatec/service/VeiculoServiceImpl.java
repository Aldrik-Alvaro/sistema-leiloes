package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.DispositivoInformatica;
import com.fatec.entity.Leilao;
import com.fatec.entity.Veiculo;
import com.fatec.repository.LeilaoRepository;
import com.fatec.repository.VeiculoRepository;
import com.fatec.exception.ResourceNotFoundException;
import com.fatec.exception.InvalidOperationException;

@Singleton
public class VeiculoServiceImpl implements VeiculoService {

    @Inject
    private final VeiculoRepository veiculoRepository;
    private final LeilaoRepository leilaoRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, LeilaoRepository leilaoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.leilaoRepository = leilaoRepository;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        if (veiculo.getLeilao() == null || leilaoRepository.findById(veiculo.getLeilao().getId()).isEmpty()) {
            throw new ResourceNotFoundException("Leilão não encontrado ou inválido.");
        }

        return veiculoRepository.save(veiculo);
    }

    @Override
    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Veículo com ID " + id + " não encontrado.");
                });
    }

    @Override
    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id).map(veiculoExistente -> {
            veiculoExistente.setTipo(veiculoAtualizado.getTipo());
            veiculoExistente.setMarca(veiculoAtualizado.getMarca());
            veiculoExistente.setModelo(veiculoAtualizado.getModelo());
            veiculoExistente.setAnoFabricacao(veiculoAtualizado.getAnoFabricacao());
            veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
            veiculoExistente.setChassi(veiculoAtualizado.getChassi());
            veiculoExistente.setCor(veiculoAtualizado.getCor());
            veiculoExistente.setKmRodados(veiculoAtualizado.getKmRodados());
            veiculoExistente.setValorInicial(veiculoAtualizado.getValorInicial());
            veiculoExistente.setEstadoConservacao(veiculoAtualizado.getEstadoConservacao());

            return veiculoRepository.update(veiculoExistente);
        }).orElseThrow(() -> new ResourceNotFoundException("Veículo com ID " + id + " não encontrado."));
    }

    @Override
    public void deletar(Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);

        if (veiculo.isEmpty()) {
            throw new ResourceNotFoundException("Veículo com ID " + id + " não encontrado.");
        }

        try {
            veiculoRepository.deleteById(id);
        } catch (Exception e) {
            throw new InvalidOperationException("Erro ao tentar remover o veículo com ID " + id + ".");
        }
    }

    @Override
    public void desassociarVeiculoLeilao(Long itemId, Long leilaoId, Long novoLeilaoId) {
        // Verifica se o item existe e está associado ao leilão atual
        Veiculo veiculo = veiculoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Veiculo não encontrado"));

        Leilao leilaoAtual = leilaoRepository.findById(leilaoId)
                .orElseThrow(() -> new IllegalArgumentException("Leilão atual não encontrado"));

        // Verifica se o Veiculo está associado ao leilão atual
        if (!veiculo.getLeilao().getId().equals(leilaoId)) {
            throw new IllegalArgumentException("O veiculo não está associado ao leilão atual.");
        }

        // Verifica se o Veiculo foi vendido
        //if (veiculoFoiVendido(veiculo)) {
        //    throw new IllegalStateException("Não é possível desassociar um veiculo que já foi vendido.");
        //}

        // Verifica se o novo leilão é válido
        Leilao novoLeilao = leilaoRepository.findById(novoLeilaoId)
                .orElseThrow(() -> new IllegalArgumentException("Novo leilão não encontrado."));

        // Associa o veiculo ao novo leilão
        veiculo.setLeilao(novoLeilao);
        veiculoRepository.update(veiculo);
    }

    // Método para verificar se o veiculo foi vendido
    //private boolean veiculoFoiVendido(Veiculo veiculo) {
    //    // Implementar lógica para verificar se o dispositivo foi vendido (ex: verificar se recebeu lances)
    //    // Por exemplo, retornar true se houver lances associados ao dispositivo
    //    return false; // Exemplo simplificado
    //}
}
