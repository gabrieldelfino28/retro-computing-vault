package br.gov.sp.fateczl.museu.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class PrecoConversao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valorAtual;

    // Moeda <-- (1:N) PrecoConversao
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moeda_atual_iso", nullable = false)
    private Moeda moedaAtualIso;

    @Column(name = "data_conversao_bcb", nullable = false)
    private LocalDate dataConversao;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    //Hardware 1:N ->
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hardward")
    private Hardware idHardward;
}
