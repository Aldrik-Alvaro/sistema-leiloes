package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.Veiculo;
import com.fatec.repository.VeiculoRepository;

@Singleton
public class VeiculoServiceImpl implements VeiculoService {

    @Inject
    private final VeiculoRepository veiculoRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @Override
    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
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
        }).orElseThrow(() -> new IllegalArgumentException("Veículo com ID " + id + " não encontrado"));
    }

    @Override
    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }
}
