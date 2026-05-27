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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    protected void validateSpecificFields(Console d) {
        log().info(LogMessage.VALIDATE, "Console", d.getModelo());
        FluentValidator.begin()
                .notEmpty(d.getGerecao(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getRegiaoSinal(), HardwareErr.REQUIRED_FIELD)
                .notNullObject(d.getTipo(), NullErr.NULL_OBJECT);
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
        Console c = getRepository().findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        log().info(LogMessage.DELETE, "Console", c.getId());
        getRepository().delete(c);
    }

    @Transactional(readOnly = true)
    public List<Console> searchByTipo(TipoConsole tipo) {
        List<Console> set = getRepository().findByTipo(tipo);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    public List<Console> searchByGeraco(String geracao) {
        List<Console> set = getRepository().findByGeracaoContainingIgnoreCase(geracao);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    public List<Console> searchBySinal(String regiaoSinal) {
        List<Console> set = getRepository().findByRegiaoSinalContainingIgnoreCase(regiaoSinal);
        checkEmptyList(set);
        return set;
    }
}
