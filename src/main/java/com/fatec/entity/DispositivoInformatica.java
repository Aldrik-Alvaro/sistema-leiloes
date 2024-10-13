package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Table(name = "dispositivo")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class DispositivoInformatica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String especificacoes;

    @Column(nullable = false)
    private double valorInicial;

    @ManyToOne
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao;

}
