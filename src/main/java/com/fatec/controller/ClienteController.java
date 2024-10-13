package com.fatec.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

import com.fatec.entity.Cliente;
import com.fatec.service.ClienteService;


@Controller("/clientes")
public class ClienteController {

    @Inject
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Get
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    @Get("/{id}")
    public HttpResponse<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    @Post
    public HttpResponse<Cliente> salvar(@Body Cliente cliente) {
        Cliente novoCliente = clienteService.salvar(cliente);
        return HttpResponse.status(HttpStatus.CREATED).body(novoCliente);
    }

    @Put("/{id}")
    public HttpResponse<Cliente> atualizar(@PathVariable Long id, @Body Cliente cliente) {
        Cliente updated = clienteService.atualizar(id, cliente);
        if (updated != null) {
            return HttpResponse.ok(updated);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{id}")
    public HttpResponse<Void> remover(@PathVariable Long id) {
        clienteService.deletar(id);
        return HttpResponse.noContent();
    }
}
