package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.domain.enums.TipoConsole;
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
public class Console extends Dispositivo{

    @Column(name="geracao", length = 40)
    private String geracao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_console", nullable = false)
    private TipoConsole tipo;

    @Column(name="regiao_sinal", length = 30)
    private String regiaoSinal;

    @Override
    public void validate() {
        super.validate();

        FluentValidator.begin()
                .notEmpty(geracao,      HardwareErr.REQUIRED_FIELD, Fields.geracao)
                .notNullObject(tipo,    HardwareErr.REQUIRED_FIELD, Fields.tipo)
                .notEmpty(regiaoSinal,  HardwareErr.REQUIRED_FIELD, Fields.regiaoSinal)
        ;
    }
}
