package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.DispositivoMovel;
import br.gov.sp.fateczl.museu.repository.DispositivoMovelRepository;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DispositivoMovelServiceImpl extends DispositivoServiceTemplate<DispositivoMovel, DispositivoMovelRepository> {

    private final DispositivoMovelRepository repository;

    public DispositivoMovelServiceImpl(DispositivoMovelRepository repository) {
        this.repository = repository;
    }

    /**
     * @implNote | Interface Abstrata de HardwareService
     */
    @Override
    protected DispositivoMovelRepository getRepository() {
        return this.repository;
    }

    @Override
    protected void validateBusinessRules(DispositivoMovel hardware) {

    }

    /**
     * @param hardware
     * Receiving harware entity to persist it on the database
     * @return repository.save(hardware)
     */
    @Override
    @Transactional
    protected DispositivoMovel save(DispositivoMovel hardware) {
        var saved = repository.save(hardware);
        log().info(LogMessage.SAVE, entity(), saved.getId());
        return saved;
    }

    @Override
    protected void beforeInsert(DispositivoMovel hardware) {

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var device = orElseNotFound(repository.findById(id));
        log().info(LogMessage.DELETE, entity(), device.getId());
        repository.delete(device);
    }

    /**
     * @param current
     * Current entity that needs to updated
     * @param incoming
     * Entity that has the updated date from the front-end
     */
    @Override
    protected void applySpecificUpdates(DispositivoMovel current, DispositivoMovel incoming) {
        current.setPolegadasTela(incoming.getPolegadasTela());
        current.setTecnologiaTela(incoming.getTecnologiaTela());
        current.setBateriaMah(incoming.getBateriaMah());
        current.setCameras(incoming.getCameras());
        current.setSensores(incoming.getSensores());
    }

    /**
     * @implNote
     * SearchBy methods implementing DispositivoMovelRepository specific fields
     */

    @Transactional(readOnly = true)
    public List<DispositivoMovel> searchByTecnologiaTela(String tela) {
        return orElseNotFound(repository.findByTecnologiaTelaContainingIgnoreCase(tela));
    }

    @Transactional(readOnly = true)
    public List<DispositivoMovel> searchByBateriaMaH(Integer mah) {
        return orElseNotFound(repository.findByBateriaMah(mah));
    }
}
