package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Console;
import br.gov.sp.fateczl.museu.domain.enums.TipoConsole;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.ConsoleRepository;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsoleService extends DispositivoServiceTemplate<Console, ConsoleRepository> {

    private final ConsoleRepository repository;

    public ConsoleService(ConsoleRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ConsoleRepository getRepository() {
        return this.repository;
    }

    @Override
    protected void validateSpecificFields(Console c) {
        log().info(LogMessage.VALIDATE, "Console", c.getModelo());
        FluentValidator.begin()
                .notEmpty(c.getGerecao(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(c.getRegiaoSinal(), HardwareErr.REQUIRED_FIELD)
                .notNullObject(c.getTipo(), NullErr.NULL_OBJECT);
    }

    @Override
    protected void applySpecificUpdates(Console current, Console incoming) {
        current.setTipo(incoming.getTipo());
        current.setGerecao(incoming.getGerecao());
        current.setRegiaoSinal(incoming.getRegiaoSinal());
    }

    @Override
    @Transactional
    protected Console save(Console hardware) {
        log().info(LogMessage.SAVE, "Console", hardware.getId());
        return getRepository().save(hardware);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var console = getRepository().findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        log().info(LogMessage.DELETE, "Console", console.getId());
        getRepository().delete(console);
    }

    @Transactional(readOnly = true)
    public List<Console> searchByTipo(TipoConsole tipo) {
        var consoles = getRepository().findByTipo(tipo);
        checkEmptyList(consoles);
        return consoles;
    }

    @Transactional(readOnly = true)
    public List<Console> searchByGeraco(String geracao) {
        var consoles = getRepository().findByGeracaoContainingIgnoreCase(geracao);
        checkEmptyList(consoles);
        return consoles;
    }

    @Transactional(readOnly = true)
    public List<Console> searchBySinal(String regiaoSinal) {
        var consoles = getRepository().findByRegiaoSinalContainingIgnoreCase(regiaoSinal);
        checkEmptyList(consoles);
        return consoles;
    }
}
