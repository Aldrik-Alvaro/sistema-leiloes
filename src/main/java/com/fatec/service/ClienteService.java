package com.fatec.service;

import java.util.List;
import java.util.Optional;

import com.fatec.entity.Cliente;

public interface ClienteService {

    List<Cliente> listarTodos();

    Optional<Cliente> buscarPorId(Long id);

    Cliente salvar(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
