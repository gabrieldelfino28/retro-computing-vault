package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.domain.enums.TipoComputador;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@FieldNameConstants
@SuperBuilder(toBuilder = true)
public class Computador extends Dispositivo{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_computador", nullable = false)
    private TipoComputador tipo;

    @Column(name = "expansibilidade", columnDefinition = "TEXT", nullable = false)
    private String expansibilidade;

    @Column(name = "teclado_descricao", columnDefinition = "TEXT", nullable = false)
    private String tecladoDescricao;

    @Column(name = "resolucoes_suportadas", columnDefinition = "TEXT", nullable = false)
    private String resolucoes;

    @Override
    public void validate() {
        super.validate();

        FluentValidator.begin()
                .notNullObject(tipo,        HardwareErr.REQUIRED_FIELD, Fields.tipo)
                .notEmpty(expansibilidade,  HardwareErr.REQUIRED_FIELD, Fields.expansibilidade)
                .notEmpty(tecladoDescricao, HardwareErr.REQUIRED_FIELD, Fields.tecladoDescricao)
                .notEmpty(resolucoes,       HardwareErr.REQUIRED_FIELD, Fields.resolucoes)
        ;
    }
}
