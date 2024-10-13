package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Table(name = "leilao")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataOcorrencia;

    @Column(nullable = false)
    private LocalDate dataVisitacao;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;
}
