package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.ComputadorRepository;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.Logger;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return repository.save(hardware);
    }

    @Override
    @Transactional
    public void update(Computador h) {
        Computador updatedEntity = getRepository().findById(h.getId())
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        updatedEntity.setModelo(h.getModelo());
        updatedEntity.setFabricante(h.getFabricante());
        //... etc preguiça :(
        this.save(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info(Logger.DELETE.forEntity("Computador"));
        Computador c = repository.findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        repository.delete(c);
    }
}
