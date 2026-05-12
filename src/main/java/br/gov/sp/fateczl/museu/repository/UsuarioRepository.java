package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailContainingIgnoreCase(String email);

    Optional<Usuario> findByNomeContainingIgnoreCase(String nome);

    List<Usuario> findByEspecialidadeContainingIgnoreCase(String especialidade);
}
