package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Console;
import br.gov.sp.fateczl.museu.domain.enums.TipoConsole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends DispositivoRepository<Console> {

    List<Console> findByGeracaoContainingIgnoreCase(String geracao);

    List<Console> findByRegiaoSinalContainingIgnoreCase(String regiaoSinal);

    List<Console> findByTipo(TipoConsole tipo);
}
