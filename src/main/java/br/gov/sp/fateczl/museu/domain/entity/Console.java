package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.domain.enums.TipoConsole;
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
public class Console extends Dispositivo{

    @Column(name="geracao", length = 40)
    private String gerecao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_console", nullable = false)
    private TipoConsole tipo;

    @Column(name="regiao_sinal", length = 30)
    private String regiaoSinal;
}
