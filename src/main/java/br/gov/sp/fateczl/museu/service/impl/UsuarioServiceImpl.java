package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.Usuario;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.UsuarioRepository;
import br.gov.sp.fateczl.museu.service.BaseService;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import br.gov.sp.fateczl.museu.util.service.ServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements ServiceSupport, BaseService<Usuario, Long> {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Usuario insert(Usuario entity) {
        checkFields(entity);
        return save(entity);
    }

    @Override
    @Transactional
    public void update(Usuario incoming) {
        checkFields(incoming);
        var current = orElseNotFound(repository.findById(incoming.getId()));
        current.setNome(incoming.getNome());
        current.setEmail(incoming.getEmail());
        current.setEspecialidade(incoming.getEspecialidade());
        current.setPassword(incoming.getPassword());
        save(current);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario searchById(Long id) {
        return orElseNotFound(repository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getAll() {
        return orElseNotFound(repository.findAll());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var user = searchById(id);
        log().info(LogMessage.DELETE, entity(), user.getId());
        repository.delete(user);
    }

    @Transactional(readOnly = true)
    public Usuario searchByEmail(String email) {
        return orElseNotFound(repository.findByEmailContainingIgnoreCase(email));
    }

    @Transactional(readOnly = true)
    public Usuario searchByNome(String nome) {
        return orElseNotFound(repository.findByNomeContainingIgnoreCase(nome));
    }

    public List<Usuario> searchByEspec(String especialidade) {
        return orElseNotFound(repository.findByEspecialidadeContainingIgnoreCase(especialidade));
    }

    private void checkFields(Usuario u) {
        FluentValidator.begin().notNullObject(u, NullErr.NULL_OBJECT, entity());
        u.validate();
    }

    private Usuario save(Usuario u){
        log().info(LogMessage.SAVE, entity(), u.getId());
        return repository.save(u);
    }
}
