package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class DispositivoMovel extends Dispositivo{

    @Column(name = "tamanho_tela_polegadas")
    private Double polegadasTela;

    @Column(name = "tecnologia_tela")
    private String tecnologiaTela;

    @Column(name = "bateria_capacidade_mah")
    private Integer bateriaMah;

    @Column(name = "cameras_detalhes", columnDefinition = "TEXT")
    private String cameras;

    @Column(name = "sensores")
    private String sensores;

    @Override
    public void validate() {
        super.validate();

        FluentValidator.begin()
                .notNullObject(polegadasTela, HardwareErr.REQUIRED_FIELD, Fields.polegadasTela)
                .notEmpty(tecnologiaTela,     HardwareErr.REQUIRED_FIELD, Fields.tecnologiaTela)
                .notNullObject(bateriaMah,    HardwareErr.REQUIRED_FIELD, Fields.bateriaMah)
                .notEmpty(cameras,            HardwareErr.REQUIRED_FIELD, Fields.cameras)
                .notEmpty(sensores,           HardwareErr.REQUIRED_FIELD, Fields.sensores)
        ;
    }
}
