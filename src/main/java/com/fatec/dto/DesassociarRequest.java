package com.fatec.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable.Deserializable
@Introspected
@Data
public class DesassociarRequest {
    private Long leilaoId;
    private Long novoLeilaoId;
}