package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.*;
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
public abstract class Dispositivo extends Hardware{

    @Column(name = "cpu", length = 150, nullable = false)
    private String cpu;

    @Column(name = "sistema_operacional", length = 512, nullable = false)
    private String sistemaOperacional;

    @Column(name = "linguagem_embutida", length = 512, nullable = false)
    private String linguagemEmbutida;

    @Enumerated(EnumType.STRING)
    @Column(name = "ram_unidade", nullable = false)
    private UnidadeMemoria ramUnidade;

    @Column(name = "ram_quantidade", nullable = false)
    private Integer ramQuantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "rom_unidade", nullable = false)
    private UnidadeMemoria romUnidade;

    @Column(name = "rom_quantidade", nullable = false)
    private Integer romQuantidade;

    @Column(name = "midia_armazenamento", nullable = false, length = 200)
    private String midiaArmazenamento;

    @Column(name = "interfaces_in_out", nullable = false, columnDefinition = "TEXT")
    private String interfacesInOut;

    @Column(name = "video", nullable = false)
    private String video;

    @Column(name = "audio", nullable = false)
    private String audio;

    @Column(name = "arquitetura_base", nullable = false, length = 90)
    private String arquiteturaBase;

    @Column(name = "design_exterior", nullable = false, columnDefinition = "TEXT")
    private String designExterior;

    @Column(name = "energia", nullable = false, length = 100)
    private String energia;

    @Transient
    public long getPesoRam() {
        if(ramUnidade == null || ramQuantidade == null) return 0L;
        return ramUnidade.computeWeight(ramQuantidade);
    }

    @Transient
    public long getPesoRom() {
        if(romUnidade == null || romQuantidade == null) return 0L;
        return romUnidade.computeWeight(romQuantidade);
    }

    @Override
    public void validate() {
        super.validate(); // ← Hardware valida primeiro

        FluentValidator.begin()
                .notEmpty(cpu,                HardwareErr.REQUIRED_FIELD, Fields.cpu)
                .notEmpty(sistemaOperacional, HardwareErr.REQUIRED_FIELD, Fields.sistemaOperacional)
                .notEmpty(linguagemEmbutida,  HardwareErr.REQUIRED_FIELD, Fields.linguagemEmbutida)
                .notNullObject(ramUnidade,    HardwareErr.REQUIRED_FIELD, Fields.ramUnidade)
                .notNullObject(ramQuantidade, HardwareErr.REQUIRED_FIELD, Fields.ramQuantidade)
                .notNullObject(romUnidade,    HardwareErr.REQUIRED_FIELD, Fields.romUnidade)
                .notNullObject(romQuantidade, HardwareErr.REQUIRED_FIELD, Fields.romQuantidade)
                .notEmpty(midiaArmazenamento, HardwareErr.REQUIRED_FIELD, Fields.midiaArmazenamento)
                .notEmpty(interfacesInOut,    HardwareErr.REQUIRED_FIELD, Fields.interfacesInOut)
                .notEmpty(video,              HardwareErr.REQUIRED_FIELD, Fields.video)
                .notEmpty(audio,              HardwareErr.REQUIRED_FIELD, Fields.audio)
                .notEmpty(arquiteturaBase,    HardwareErr.REQUIRED_FIELD, Fields.arquiteturaBase)
                .notEmpty(designExterior,     HardwareErr.REQUIRED_FIELD, Fields.designExterior)
                .notEmpty(energia,            HardwareErr.REQUIRED_FIELD, Fields.energia)
        ;
    }
}
