package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.DeviceErr;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.AppInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class DispositivoServiceTemplate<T extends Dispositivo> extends HardwareServiceTemplate<T> {

    @Override
    protected void validateDeviceFields(T d) {
        if (d.getSistemaOperacional() == null || d.getSistemaOperacional().isBlank())
            d.setSistemaOperacional(AppInfo.DEFAULT_OS.getInfo());

        FluentValidator.begin()
                .notEmpty(d.getCpu(), DeviceErr.CPU_REQUIRED)
                .notEmpty(d.getLinguagemEmbutida(), DeviceErr.BUILTIN_LOGIC_REQUIRED)

                .notEmpty(d.getArquiteturaBase(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getVideo(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getAudio(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getDesignExterior(), HardwareErr.REQUIRED_FIELD)
                .notEmpty(d.getEnergia(), HardwareErr.REQUIRED_FIELD)

                .notEmpty(d.getInterfacesInOut(), DeviceErr.INTERFACES_REQUIRED)
                .notEmpty(d.getMidiaArmazenamento(), DeviceErr.STORAGE_MEDIA_REQUIRED)

                .notNullObject(d.getRamUnidade(), DeviceErr.RAM_UNIT_REQUIRED)
                .check(d.getRamQuantidade() == null || d.getRamQuantidade() <= 0, DeviceErr.RAM_INVALID_QUANTITY)

                .notNullObject(d.getRomUnidade(), DeviceErr.ROM_UNIT_REQUIRED)
                .check(d.getRomQuantidade() == null || d.getRamQuantidade() <= 0, DeviceErr.ROM_INVALID_QUANTITY)
        ;
        validateSpecificFields(d);
    }

    @Transactional(readOnly = true)
    public final T searchById(Long id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NULL_FIELD));
    }

    @Transactional(readOnly = true)
    public final List<T> getAll() {
        return getRepository().findAll();
    }

    /**
     *  HardwareRepository searchBy methods
     */

    @Transactional(readOnly = true)
    public final List<T> searchByModelo(String model) {
        List<T> res = getRepository().findByModeloContainingIgnoreCase(model);
        checkEmpty(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final List<T> searchByFabricante(String fabricante) {
        List<T> res = getRepository().findByFabricanteContainingIgnoreCase(fabricante);
        checkEmpty(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final List<T> searchByDataLancamento(LocalDate data) {
        List<T> res = getRepository().findByDataLancamento(data);
        checkEmpty(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final List<T> searchByPais(String pais) {
        List<T> res = getRepository().findByPaisOrigemContainingIgnoreCase(pais);
        checkEmpty(res);
        return res;
    }

    /**
     * DispositivoRepository searchBy methods
     */


    private void checkEmpty(List<T> resultSet) {
        FluentValidator.begin().check(resultSet.isEmpty(), NullErr.NOT_FOUND);
    }

    protected abstract void validateSpecificFields(T t);

    protected abstract DispositivoRepository<T> getRepository();
}
