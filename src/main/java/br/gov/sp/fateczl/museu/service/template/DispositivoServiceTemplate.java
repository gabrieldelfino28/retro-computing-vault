package br.gov.sp.fateczl.museu.service.template;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import br.gov.sp.fateczl.museu.exception.codes.DeviceErr;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.service.DispositivoService;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.AppInfo;
import br.gov.sp.fateczl.museu.util.enums.DeviceComparator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class DispositivoServiceTemplate
        <Type extends Dispositivo, Repository extends DispositivoRepository<Type>>
        extends HardwareServiceTemplate<Type, Repository>
        implements DispositivoService<Type> {

    @Override
    protected void validateDeviceFields(Type d) {
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
                .check(d.getRomQuantidade() == null || d.getRomQuantidade() <= 0, DeviceErr.ROM_INVALID_QUANTITY)
        ;
        validateSpecificFields(d);
    }

    @Override
    protected void applyInheritedUpdates(Type current, Type incoming) {
        applyDeviceUpdates(current, incoming);
        applySpecificUpdates(current, incoming);
    }

    private void applyDeviceUpdates(Type current, Type incoming) {
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

    /**
     * DispositivoRepository searchBy methods
     */

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByCpu(String cpu) {
        List<Type> set = getRepository().findByCpuContainingIgnoreCase(cpu);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByRamUnidade(UnidadeMemoria unidade) {
        List<Type> set = getRepository().findByRamUnidade(unidade);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByRomUnidade(UnidadeMemoria unidade) {
        List<Type> set = getRepository().findByRomUnidade(unidade);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchBySistemaOperacional(String OS) {
        List<Type> set = getRepository().findBySistemaOperacionalContainingIgnoreCase(OS);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByLinguagem(String linguagem) {
        List<Type> set = getRepository().findByLinguagemEmbutidaContainingIgnoreCase(linguagem);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByRamMinima(UnidadeMemoria unidade, Integer quantidade, DeviceComparator order) {
        long pesoMinimo = unidade.computeWeight(quantidade);

        List<Type> res = getAll()
                .stream()
                .filter(d -> d.getPesoRam() >= pesoMinimo).toList();
        return sort(res, order);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByRomMinima(UnidadeMemoria unidade, Integer quantidade, DeviceComparator order) {
        long pesoMinimo = unidade.computeWeight(quantidade);

        List<Type> res = getAll()
                .stream()
                .filter(d -> d.getPesoRom() >= pesoMinimo).toList();
        return sort(res, order);
    }

    protected List<Type> sort(List<Type> set, DeviceComparator c) {
        return set.stream().sorted(c.get()).toList();
    }

    protected abstract void validateSpecificFields(Type d);

    protected abstract void applySpecificUpdates(Type current, Type incoming);
}