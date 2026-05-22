package br.gov.sp.fateczl.museu.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Data
@ToString
@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    @Column(name = "caminho_url", nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(name = "descricao_legenda", length = 300)
    private String descricao;

    @Column(name = "eh_principal")
    private boolean ehPrincipal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hardware", nullable = false)
    private Hardware hardware;

    public Imagem() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem imagem = (Imagem) o;
        return Objects.equals(uuid, imagem.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}