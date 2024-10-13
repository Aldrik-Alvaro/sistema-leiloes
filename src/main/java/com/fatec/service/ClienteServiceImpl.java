package com.fatec.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import com.fatec.entity.Cliente;
import com.fatec.repository.ClienteRepository;

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
        return repository.findById(id);
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Optional<Cliente> existing = repository.findById(id);
        if (existing.isPresent()) {
            Cliente updated = existing.get();
            updated.setNome(cliente.getNome());
            updated.setCpf(cliente.getCpf());
            updated.setEmail(cliente.getEmail());
            updated.setEndereco(cliente.getEndereco());
            updated.setTelefone(cliente.getTelefone());
            return repository.update(updated);
        } else {
            return null;
        }
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}