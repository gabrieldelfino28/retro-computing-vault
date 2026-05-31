package br.gov.sp.fateczl.museu.domain.entity;

import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@FieldNameConstants
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 160)
    private String email;

    @Column(nullable = false, length = 70)
    private String especialidade;

    @Column(nullable = false)
    private String password;

    public void validate() {
        FluentValidator.begin()
                .notEmpty(nome,          NullErr.NULL_FIELD, Fields.nome)
                .notEmpty(email,         NullErr.NULL_FIELD, Fields.email)
                .notEmpty(especialidade, NullErr.NULL_FIELD, Fields.especialidade)
                .notEmpty(password,      NullErr.NULL_FIELD, Fields.password)
        ;
    }
}
