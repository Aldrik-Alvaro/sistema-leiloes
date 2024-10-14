package com.fatec.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

import com.fatec.entity.Leilao;
import com.fatec.service.LeilaoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller("/leiloes")
@Tag(name = "Leilao")
public class LeilaoController {

    @Inject
    private final LeilaoService leilaoService;

    public LeilaoController(LeilaoService leilaoService) {
        this.leilaoService = leilaoService;
    }

    @Get
    public List<Leilao> listar() {
        return leilaoService.listarTodos();
    }

    @Post
    public HttpResponse<Leilao> criar(@Body Leilao leilao) {
        Leilao novoLeilao = leilaoService.salvar(leilao);
        return HttpResponse.status(HttpStatus.CREATED).body(novoLeilao);
    }


    @Get("/{id}")
    public HttpResponse<Leilao> buscarPorId(@PathVariable Long id) {
        return leilaoService.buscarPorId(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    @Put("/{id}")
    public HttpResponse<Leilao> atualizar(@PathVariable Long id, @Body Leilao leilaoAtualizado) {
        return HttpResponse.ok(leilaoService.atualizar(id, leilaoAtualizado));
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        leilaoService.deletar(id);
        return HttpResponse.noContent();
    }
}

