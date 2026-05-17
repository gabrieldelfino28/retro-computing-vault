package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.ComputadorRepository;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.Logger;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class ComputadorService extends DispositivoServiceTemplate<Computador> {

    private final ComputadorRepository repository;

    @Override
    protected DispositivoRepository<Computador> getRepository() {
        return this.repository;
    }

    public ComputadorService(ComputadorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void validateSpecificFields(Computador c) {
        log.info(Logger.VALIDATE.forEntity("Computador"), c.getModelo());
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
        log.info(Logger.SAVE.forEntity("Computador"), hardware.getId());
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

    @Transactional
    public void deleteById(Long id) {
        log.info(Logger.DELETE.forEntity("Computador"));
        Computador c = repository.findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        repository.delete(c);
    }
}