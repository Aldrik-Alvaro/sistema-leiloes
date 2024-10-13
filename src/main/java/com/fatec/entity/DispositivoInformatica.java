package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Data
@NoArgsConstructor
@Entity
@Serdeable
public class DispositivoInformatica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;  // Ex: Notebook, Monitor
    private String marca;
    private String modelo;
    private String especificacoes;

    // Outros atributos relevantes para o dispositivo
}
