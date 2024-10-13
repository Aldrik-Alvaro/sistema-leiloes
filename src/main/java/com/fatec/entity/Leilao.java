package com.fatec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.persistence.*;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;

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

    //@OneToMany(mappedBy = "leilao", fetch = FetchType.EAGER)
    //private List<DispositivoInformatica> dispositivos;

    //@OneToMany(mappedBy = "leilao", fetch = FetchType.EAGER)
    //private List<Veiculo> veiculos;

    //@ManyToOne
    //@JoinColumn(name = "instituicao_financeira_id", nullable = false)
    //private InstituicaoFinanceira instituicaoFinanceira;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "leilao_instituicao_financeira",
            joinColumns = @JoinColumn(name = "leilao_id"),
            inverseJoinColumns = @JoinColumn(name = "instituicao_financeira_id")
    )
    private Set<InstituicaoFinanceira> instituicoesFinanceiras;

}
