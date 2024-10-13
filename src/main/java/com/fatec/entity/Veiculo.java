package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Table(name = "veiculo")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private int anoFabricacao;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false, unique = true)
    private String chassi;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private int kmRodados;

    @Column(nullable = false)
    private double valorInicial;

    @Column(nullable = false)
    private String estadoConservacao;
}
