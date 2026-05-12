package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.PrecoConversao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrecoConversaoRepository extends JpaRepository<PrecoConversao, Integer> {

    Optional<PrecoConversao> findByHardware_Id(Long hardwareId);

    List<PrecoConversao> findByDataConversao(LocalDate dataConversao);

    List<PrecoConversao> findByMoeda_Iso(String iso);
}
