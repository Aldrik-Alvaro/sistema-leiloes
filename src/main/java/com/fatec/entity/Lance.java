package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;
import java.time.LocalDateTime;

@Table(name = "lance")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private LocalDateTime dataLance = LocalDateTime.now();
}
