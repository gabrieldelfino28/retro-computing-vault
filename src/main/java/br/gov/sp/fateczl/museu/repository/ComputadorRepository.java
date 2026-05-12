package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.domain.enums.TipoComputador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputadorRepository extends DispositivoRepository<Computador> {

    List<Computador> findByTipo(TipoComputador tipo);

    List<Computador> findByResolucoesContainingIgnoreCase(String resolucoes);
}
