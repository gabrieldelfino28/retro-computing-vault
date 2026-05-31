package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Objects;
import java.util.UUID;

@Data
@ToString
@Entity
@FieldNameConstants
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

    public void validate() {
        FluentValidator.begin()
                .notEmpty(uuid,          NullErr.NULL_FIELD,  Fields.uuid)
                .notEmpty(url,           NullErr.NULL_FIELD,  Fields.url)
                .notEmpty(descricao,     NullErr.NULL_FIELD,  Fields.descricao)
                .notNullObject(hardware, NullErr.NULL_OBJECT, Fields.hardware)
        // ehPrincipal é boolean primitivo — sempre tem valor
        ;
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