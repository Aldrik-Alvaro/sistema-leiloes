package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.Cliente;
import com.fatec.repository.ClienteRepository;
import com.fatec.exception.ResourceNotFoundException;
import com.fatec.exception.InvalidOperationException;

@Singleton
public class ClienteServiceImpl implements ClienteService {

    @Inject
    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return repository.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.");
                });
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        return repository.findById(id).map(existing -> {
            existing.setNome(cliente.getNome());
            existing.setCpf(cliente.getCpf());
            existing.setEmail(cliente.getEmail());
            existing.setEndereco(cliente.getEndereco());
            existing.setTelefone(cliente.getTelefone());
            return repository.update(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    @Override
    public void deletar(Long id) {
        Optional<Cliente> cliente = repository.findById(id);

        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.");
        }

        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new InvalidOperationException("Erro ao tentar remover o cliente com ID " + id + ".");
        }
    }
}