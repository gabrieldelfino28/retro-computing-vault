package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface MoedaRepository extends JpaRepository<Moeda, String> {

    Optional<Moeda> findByIsoContainingIgnoreCase(String iso);

    Optional<Moeda> findBySimboloContainingIgnoreCase(String simbolo);

    List<Moeda> findByInicio(Year inicio);

    List<Moeda> findByFim(Year fim);
}
