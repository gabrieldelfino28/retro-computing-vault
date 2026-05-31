package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@FieldNameConstants
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

    public void validate() {
        FluentValidator.begin()
                .notNullObject(moedaAtualIso, NullErr.NULL_OBJECT,  Fields.moedaAtualIso)
                .notNullObject(dataConversao, NullErr.NULL_FIELD,   Fields.dataConversao)
                .notEmpty(observacao,         NullErr.NULL_FIELD,   Fields.observacao)
                .notNullObject(idHardward,    NullErr.NULL_OBJECT,  Fields.idHardward)
                .ifPresent(valorAtual, v ->
                        v.isPositive(valorAtual, HardwareErr.NEGATIVE_VALUE, Fields.valorAtual)
                )
        ;
    }
}
