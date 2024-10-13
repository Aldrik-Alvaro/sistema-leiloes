package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Table(name = "cliente")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String telefone;

    @Column
    private String endereco;
}
