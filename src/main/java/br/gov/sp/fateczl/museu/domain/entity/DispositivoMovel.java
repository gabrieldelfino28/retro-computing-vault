package br.gov.sp.fateczl.museu.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
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
}
