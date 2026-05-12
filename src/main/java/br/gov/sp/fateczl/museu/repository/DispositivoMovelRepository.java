package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.DispositivoMovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoMovelRepository extends JpaRepository<DispositivoMovel, Long> {

    List<DispositivoMovel> findByTecnologiaTelaContainingIgnoreCase(String tela);

    List<DispositivoMovel> findByBateriaMah(Integer mah);
}
