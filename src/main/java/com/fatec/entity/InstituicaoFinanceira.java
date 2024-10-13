package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Table(name = "instituicao_financeira")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class InstituicaoFinanceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column
    private String endereco;

    @Column
    private String telefone;
}
