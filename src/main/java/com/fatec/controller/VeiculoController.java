package com.fatec.controller;

import com.fatec.dto.DesassociarRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

import com.fatec.entity.Veiculo;
import com.fatec.service.VeiculoService;
//import io.swagger.v3.oas.annotations.tags.Tag;

@Controller("/veiculos")
//@Tag(name = "Veiculo")
public class VeiculoController {

    @Inject
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Get
    public List<Veiculo> listar() {
        return veiculoService.listarTodos();
    }

    @Post
    public HttpResponse<Veiculo> salvar(@Body Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.salvar(veiculo);
        return HttpResponse.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @Get("/{id}")
    public HttpResponse<Veiculo> buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarPorId(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    @Put("/{id}")
    public HttpResponse<Veiculo> atualizar(@PathVariable Long id, @Body Veiculo veiculoAtualizado) {
        return HttpResponse.ok(veiculoService.atualizar(id, veiculoAtualizado));
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        veiculoService.deletar(id);
        return HttpResponse.noContent();
    }

    @Post("/{veiculoId}/desassociar")
    public void desassociarVeiculo(
            @PathVariable Long veiculoId,
            @Body DesassociarRequest request) {
        veiculoService.desassociarVeiculoLeilao(veiculoId, request.getLeilaoId(), request.getNovoLeilaoId());
    }
}
