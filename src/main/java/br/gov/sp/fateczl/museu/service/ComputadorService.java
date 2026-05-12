package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.repository.ComputadorRepository;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.util.enums.Logger;
import jakarta.transaction.Transactional;
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

    }

    @Override
    protected Computador save(Computador hardware) {
        return repository.save(hardware);
    }

    @Override
    @Transactional
    public void update(Computador hardware) {
        this.save(hardware);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
