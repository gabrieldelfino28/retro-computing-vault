package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.Console;
import br.gov.sp.fateczl.museu.domain.enums.TipoConsole;
import br.gov.sp.fateczl.museu.repository.ConsoleRepository;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsoleServiceImpl extends DispositivoServiceTemplate<Console, ConsoleRepository> {

    private final ConsoleRepository repository;

    public ConsoleServiceImpl(ConsoleRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ConsoleRepository getRepository() {
        return this.repository;
    }

    @Override
    protected void validateBusinessRules(Console c) {

    }

    @Override
    protected void applySpecificUpdates(Console current, Console incoming) {
        current.setTipo(incoming.getTipo());
        current.setGeracao(incoming.getGeracao());
        current.setRegiaoSinal(incoming.getRegiaoSinal());
    }

    @Override
    @Transactional
    protected Console save(Console hardware) {
        log().info(LogMessage.SAVE, entity(), hardware.getId());
        return repository.save(hardware);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var console = orElseNotFound(repository.findById(id));
        log().info(LogMessage.DELETE, entity(), console.getId());
        repository.delete(console);
    }

    @Transactional(readOnly = true)
    public List<Console> searchByTipo(TipoConsole tipo) {
        return orElseNotFound(repository.findByTipo(tipo));
    }

    @Transactional(readOnly = true)
    public List<Console> searchByGeraco(String geracao) {
        return orElseNotFound(repository.findByGeracaoContainingIgnoreCase(geracao));
    }

    @Transactional(readOnly = true)
    public List<Console> searchBySinal(String regiaoSinal) {
        return orElseNotFound(repository.findByRegiaoSinalContainingIgnoreCase(regiaoSinal));
    }
}