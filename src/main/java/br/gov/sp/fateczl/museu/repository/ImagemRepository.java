package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    List<Imagem> findByEhPrincipal(boolean ehPrincipal);

    List<Imagem> findByHardware_Id(Long hardwareId);

    Optional<Imagem> findByHardware_IdAndEhPrincipal(Long hardwareId, boolean ehPrincipal);
}
