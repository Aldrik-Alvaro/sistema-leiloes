package com.fatec.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

import com.fatec.entity.InstituicaoFinanceira;
import com.fatec.service.InstituicaoFinanceiraService;

@Controller("/instituicoes-financeiras")
public class InstituicaoFinanceiraController {

    @Inject
    private InstituicaoFinanceiraService instituicaoFinanceiraService;

    @Get
    public List<InstituicaoFinanceira> listar() {
        return instituicaoFinanceiraService.listarTodos();
    }

    @Get("/{id}")
    public HttpResponse<InstituicaoFinanceira> buscarPorId(@PathVariable Long id) {
        return instituicaoFinanceiraService.buscarPorId(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    @Post
    public HttpResponse<InstituicaoFinanceira> salvar(@Body InstituicaoFinanceira instituicaoFinanceira) {
        InstituicaoFinanceira created = instituicaoFinanceiraService.salvar(instituicaoFinanceira);
        return HttpResponse.created(created);
    }

    @Put("/{id}")
    public HttpResponse<InstituicaoFinanceira> atualizar(@PathVariable Long id, @Body InstituicaoFinanceira instituicaoFinanceira) {
        InstituicaoFinanceira updated = instituicaoFinanceiraService.atualizar(id, instituicaoFinanceira);
        if (updated != null) {
            return HttpResponse.ok(updated);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        instituicaoFinanceiraService.deletar(id);
        return HttpResponse.noContent();
    }


}
