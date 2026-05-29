package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.DispositivoMovel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoMovelRepository extends DispositivoRepository<DispositivoMovel> {

    List<DispositivoMovel> findByTecnologiaTelaContainingIgnoreCase(String tela);

    List<DispositivoMovel> findByBateriaMah(Integer mah);
}
