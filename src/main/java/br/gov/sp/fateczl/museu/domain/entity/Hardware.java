package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_hardware")
    private Long id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String fabricante;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "pais_origem", nullable = false)
    private String paisOrigem;

    @Column(nullable = false)
    private String observacao;

    @Column(name = "linha_produto", nullable = false)
    private String linhaProduto;

    @Column(name = "valor_original", precision = 20, scale = 2, nullable = true)
    private BigDecimal valorOriginal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "moeda_iso",
            referencedColumnName = "iso",
            columnDefinition = "CHAR(3)"
    )
    private Moeda moedaISO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrado_por", nullable = false)
    private Usuario registradoPor;

    @OneToMany(mappedBy = "hardware", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Imagem> imagens = new HashSet<>();

    public void addImagem(Imagem img) {
        this.imagens.add(img);
        img.setHardware(this);
    }

    public void validate() {
        FluentValidator.begin()
                .notEmpty(modelo,       HardwareErr.REQUIRED_FIELD, Fields.modelo)
                .notEmpty(fabricante,   HardwareErr.REQUIRED_FIELD, Fields.fabricante)
                .notEmpty(descricao,    HardwareErr.REQUIRED_FIELD, Fields.descricao)
                .notEmpty(paisOrigem,   HardwareErr.REQUIRED_FIELD, Fields.paisOrigem)
                .notEmpty(observacao,   HardwareErr.REQUIRED_FIELD, Fields.observacao)
                .notEmpty(linhaProduto, HardwareErr.REQUIRED_FIELD, Fields.linhaProduto)
                .notNullObject(dataLancamento, HardwareErr.REQUIRED_FIELD, Fields.dataLancamento)
                .notInFuture(dataLancamento,   HardwareErr.INVALID_DATE,   Fields.dataLancamento)
                .minYear(dataLancamento, 1940,  HardwareErr.ANO_INVALIDO,   Fields.dataLancamento)
                .ifPresent(valorOriginal, v ->
                        v.isPositive(valorOriginal, HardwareErr.NEGATIVE_VALUE, Fields.valorOriginal)
                )
                .notNullObject(registradoPor, NullErr.NULL_OBJECT, Fields.registradoPor)
        ;
    }
}
