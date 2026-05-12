package br.gov.sp.fateczl.museu.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private List<Imagem> imagens = new ArrayList<>();

    public void addImagem(Imagem img) {
        this.imagens.add(img);
        img.setHardware(this);
    }
}
