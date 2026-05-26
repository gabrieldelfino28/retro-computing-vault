package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.ComputadorRepository;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputadorService extends DispositivoServiceTemplate<Computador, ComputadorRepository> {

    private final ComputadorRepository repository;

    @Override
    protected ComputadorRepository getRepository() {
        return this.repository;
    }

    public ComputadorService(ComputadorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void validateSpecificFields(Computador c) {
        log.info(LogMessage.VALIDATE.forEntity("Computador"), c.getModelo());
        FluentValidator.begin()
                .notNullObject(c.getTipo(), NullErr.NULL_OBJECT)
                .notEmpty(c.getExpansibilidade(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(c.getTecladoDescricao(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(c.getResolucoes(), HardwareErr.REQUIRED_FIELD)
        ;
    }

    @Override
    @Transactional
    protected Computador save(Computador hardware) {
        log.info(LogMessage.SAVE, "Computador", hardware.getId());
        return getRepository().save(hardware);
    }

    @Override
    @Transactional
    protected void applySpecificUpdates(Computador existing, Computador incoming) {
        existing.setTipo(incoming.getTipo());
        existing.setExpansibilidade(incoming.getExpansibilidade());
        existing.setTecladoDescricao(incoming.getTecladoDescricao());
        existing.setResolucoes(incoming.getResolucoes());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Computador c = repository.findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        log.info(LogMessage.DELETE, "Computador",c.getId());
        repository.delete(c);
    }
}