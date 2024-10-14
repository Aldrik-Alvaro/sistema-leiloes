package com.fatec.controller;

import com.fatec.dto.DesassociarRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

import com.fatec.entity.DispositivoInformatica;
import com.fatec.service.DispositivoService;
//import io.swagger.v3.oas.annotations.tags.Tag;

@Controller("/dispositivos")
//@Tag(name = "Dispositivo")
public class DispositivoController {

    @Inject
    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @Get
    public List<DispositivoInformatica> listar() {
        return dispositivoService.listarTodos();
    }


    @Post
    public HttpResponse<DispositivoInformatica> salvar(@Body DispositivoInformatica dispositivo) {
        DispositivoInformatica novoDispositivo = dispositivoService.salvar(dispositivo);
        return HttpResponse.status(HttpStatus.CREATED).body(novoDispositivo);
    }

    @Get("/{id}")
    public HttpResponse<DispositivoInformatica> buscarPorId(@PathVariable Long id) {
        return dispositivoService.buscarPorId(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    @Put("/{id}")
    public HttpResponse<DispositivoInformatica> atualizar(@PathVariable Long id, @Body DispositivoInformatica dispositivoAtualizado) {
        return HttpResponse.ok(dispositivoService.atualizar(id, dispositivoAtualizado));
    }

    @Delete("/{id}")
    public HttpResponse<Void> remover(@PathVariable Long id) {
        dispositivoService.deletar(id);
        return HttpResponse.noContent();
    }

    @Post("/{dispositivoId}/desassociar")
    public void desassociarDispositivo(
            @PathVariable Long dispositivoId,
            @Body DesassociarRequest request) {
        dispositivoService.desassociarDispositivoDeLeilao(dispositivoId, request.getLeilaoId(), request.getNovoLeilaoId());
    }
}

