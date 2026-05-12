package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface HardwareRepository<T extends Hardware> extends JpaRepository<T, Long> {

    List<T> findByModeloContainingIgnoreCase(String modelo);

    List<T> findByFabricanteContainingIgnoreCase(String fabricante);

    List<T> findByDataLancamento(LocalDate dataLancamento);

    List<T> findByPaisOrigemContainingIgnoreCase(String paisOrigem);
}
