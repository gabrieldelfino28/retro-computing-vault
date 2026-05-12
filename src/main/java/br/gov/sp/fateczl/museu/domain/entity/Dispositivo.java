package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public abstract class Dispositivo extends Hardware{

    @Column(name = "cpu", length = 150, nullable = false)
    private String cpu;

    @Column(name = "sistema_operacional", length = 512)
    private String sistemaOperacional;

    @Column(name = "linguagem_embutida", length = 512)
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
}
