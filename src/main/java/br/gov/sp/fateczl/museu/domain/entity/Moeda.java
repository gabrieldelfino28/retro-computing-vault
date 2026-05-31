package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@FieldNameConstants
public class Moeda {

    @Id
    @Column(columnDefinition = "CHAR(3)", length = 3, nullable = false)
    private String iso;

    @Column(length = 10, nullable = false)
    private String simbolo;

    @Column(nullable = false)
    private Year inicio;

    @Column(nullable = false)
    private Year fim;

    public void validate() {
        FluentValidator.begin()
                .notEmpty(iso,         NullErr.NULL_FIELD, Fields.iso)
                .notEmpty(simbolo,     NullErr.NULL_FIELD, Fields.simbolo)
                .notNullObject(inicio, NullErr.NULL_FIELD, Fields.inicio)
                .notNullObject(fim,    NullErr.NULL_FIELD, Fields.fim)
        ;
    }
}
