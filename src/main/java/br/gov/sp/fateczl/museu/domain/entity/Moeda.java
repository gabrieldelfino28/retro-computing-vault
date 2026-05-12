package br.gov.sp.fateczl.museu.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
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
}
