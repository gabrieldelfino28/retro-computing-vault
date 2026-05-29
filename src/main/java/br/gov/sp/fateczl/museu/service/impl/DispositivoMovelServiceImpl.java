package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.DispositivoMovel;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.DispositivoMovelRepository;
import br.gov.sp.fateczl.museu.service.template.DispositivoServiceTemplate;
import br.gov.sp.fateczl.museu.util.FluentValidator;
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

    /**
     * @param hardware
     * Receiving harware entity to persist it on the database
     * @return repository.save(hardware)
     */
    @Override
    @Transactional
    protected DispositivoMovel save(DispositivoMovel hardware) {
        log().info(LogMessage.SAVE, "Dispositivo Movel", hardware.getId());
        return getRepository().save(hardware);
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        var device = getRepository().findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
        log().info(LogMessage.DELETE, "Dispositivo Movel", device.getId());
        getRepository().delete(device);
    }

    /**
     * @param d
     */
    @Override
    protected void validateSpecificFields(DispositivoMovel d) {
        log().info(LogMessage.VALIDATE, "Dispositivo Movel", d.getModelo());
        FluentValidator.begin()
                .notNullObject(d.getPolegadasTela(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getTecnologiaTela(), HardwareErr.REQUIRED_FIELD)
                .notNullObject(d.getBateriaMah(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getCameras(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getSensores(), HardwareErr.REQUIRED_FIELD);
    }

    /**
     * @param current
     * @param incoming
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
        var mobileDevices = getRepository().findByTecnologiaTelaContainingIgnoreCase(tela);
        checkEmptyList(mobileDevices);
        return mobileDevices;
    }

    @Transactional(readOnly = true)
    public List<DispositivoMovel> searchByBateriaMaH(Integer mah) {
        var mobileDevices = getRepository().findByBateriaMah(mah);
        checkEmptyList(mobileDevices);
        return mobileDevices;
    }
}
