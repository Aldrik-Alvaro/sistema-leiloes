package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;

@Table(name = "item_nao_vendido")
@Data
@NoArgsConstructor
@Entity
@Serdeable
public class ItemNaoVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoItem;

    @Column(nullable = false)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "leilao_atual_id", nullable = false)
    private Leilao leilaoAtual;

    @ManyToOne
    @JoinColumn(name = "novo_leilao_id", nullable = false)
    private Leilao novoLeilao;

    @Column
    private String motivo;
}
