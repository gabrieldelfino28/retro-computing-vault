package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
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

    @Override
    protected ComputadorRepository getRepository() {
        return this.repository;
    }

    @Override
    protected void validateBusinessRules(Computador c) {

    }

    public ComputadorServiceImpl(ComputadorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    protected Computador save(Computador c) {
        log().info(LogMessage.SAVE, entity(), c.getId());
        return repository.save(c);
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
}