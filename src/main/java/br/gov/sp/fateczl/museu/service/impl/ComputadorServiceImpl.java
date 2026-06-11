package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.domain.entity.Usuario;
import br.gov.sp.fateczl.museu.domain.enums.TipoComputador;
import br.gov.sp.fateczl.museu.repository.ComputadorRepository;
import br.gov.sp.fateczl.museu.service.ComputadorService;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComputadorServiceImpl extends DispositivoServiceTemplate<Computador, ComputadorRepository> implements ComputadorService {

    private final ComputadorRepository repository;
    private final UsuarioServiceImpl usuarioService; //Mock de usuario

    @Override
    protected ComputadorRepository getRepository() {
        return this.repository;
    }

    public ComputadorServiceImpl(ComputadorRepository repository, UsuarioServiceImpl usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void validateBusinessRules(Computador c) {

    }

    @Override
    @Transactional
    protected Computador save(Computador hardware) {
        var saved = repository.save(hardware);
        log().info(LogMessage.SAVE, entity(), saved.getId());
        return saved;
    }

    @Override
    protected void beforeInsert(Computador hardware) {
        Usuario usuario = usuarioService.searchById(1L);
        hardware.setRegistradoPor(usuario);

    }

    /**
     * @param existing
     * Current existing entity that needs to updated
     * @param incoming
     * Entity that has the updated date from the front-end
     */
    @Override
    protected void applySpecificUpdates(Computador existing, Computador incoming) {
        existing.setTipo(incoming.getTipo());
        existing.setExpansibilidade(incoming.getExpansibilidade());
        existing.setTecladoDescricao(incoming.getTecladoDescricao());
        existing.setResolucoes(incoming.getResolucoes());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var comp = orElseNotFound(repository.findById(id));
        log().info(LogMessage.DELETE, entity(), comp.getId());
        repository.delete(comp);
    }

    /**
     * @implNote
     * SearchBy methods implementing ComputadorRepository specific fields
     */

    @Override
    @Transactional(readOnly = true)
    public List<Computador> searchByTipo(TipoComputador tipo) {
        return orElseNotFound(repository.findByTipo(tipo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Computador> searchByResolucao(String res) {
        return orElseNotFound(repository.findByResolucoesContainingIgnoreCase(res));
    }

    @Override
    public String pluralEntity() {
        return entity() + "es";
    }
}