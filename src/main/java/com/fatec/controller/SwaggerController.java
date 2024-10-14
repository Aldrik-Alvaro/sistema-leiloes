package com.fatec.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller("/swagger")
@Tag(name = "swagger")
public class SwaggerController {

    @Get
    public String getSwaggerJson() throws IOException {
        String swaggerFilePath = "target/classes/META-INF/swagger/swagger.yml";

        return new String(Files.readAllBytes(Paths.get(swaggerFilePath)));
    }
}
