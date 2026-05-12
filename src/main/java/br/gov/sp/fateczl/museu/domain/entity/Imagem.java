package br.gov.sp.fateczl.museu.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "caminho_url", nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(name = "descricao_legenda", length = 300)
    private String descricao;

    @Column(name = "eh_principal")
    private boolean ehPrincipal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hardware", nullable = false)
    private Hardware hardware;
}