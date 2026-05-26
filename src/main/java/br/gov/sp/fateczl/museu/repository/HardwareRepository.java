package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("ALL")
@NoRepositoryBean
public interface HardwareRepository<Type extends Hardware> extends JpaRepository<Type, Long> {

    List<Type> findByModeloContainingIgnoreCase(String modelo);

    List<Type> findByFabricanteContainingIgnoreCase(String fabricante);

    List<Type> findByDataLancamento(LocalDate dataLancamento);

    List<Type> findByPaisOrigemContainingIgnoreCase(String paisOrigem);
}
