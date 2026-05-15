package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.DeviceErr;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
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


    /**
     * DispositivoRepository searchBy methods
     */

    @Override
    protected void applyInheritedUpdates(T current, T incoming) {
        applyDeviceUpdates(current, incoming);
        applySpecificUpdates(current, incoming);
    }

    private void applyDeviceUpdates(T current, T incoming) {
        current.setCpu(incoming.getCpu());
        current.setSistemaOperacional(incoming.getSistemaOperacional());
        current.setLinguagemEmbutida(incoming.getLinguagemEmbutida());
        current.setRamUnidade(incoming.getRamUnidade());
        current.setRamQuantidade(incoming.getRamQuantidade());
        current.setRomUnidade(incoming.getRomUnidade());
        current.setRomQuantidade(incoming.getRomQuantidade());
        current.setMidiaArmazenamento(incoming.getMidiaArmazenamento());
        current.setInterfacesInOut(incoming.getInterfacesInOut());
        current.setVideo(incoming.getVideo());
        current.setAudio(incoming.getAudio());
        current.setArquiteturaBase(incoming.getArquiteturaBase());
        current.setDesignExterior(incoming.getDesignExterior());
        current.setEnergia(incoming.getEnergia());
    }

    protected abstract void validateSpecificFields(T d);

    protected abstract void applySpecificUpdates(T current, T incoming);
}
