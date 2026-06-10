package br.gov.sp.fateczl.museu.service.template;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.repository.UsuarioRepository;
import br.gov.sp.fateczl.museu.service.DispositivoService;
import br.gov.sp.fateczl.museu.util.enums.DeviceComparator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class DispositivoServiceTemplate
        <Type extends Dispositivo, Repository extends DispositivoRepository<Type>>
        extends HardwareServiceTemplate<Type, Repository>
        implements DispositivoService<Type> {

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
        return orElseNotFound(getRepository().findByCpuContainingIgnoreCase(cpu));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByRamUnidade(UnidadeMemoria unidade) {
        return orElseNotFound(getRepository().findByRamUnidade(unidade));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByRomUnidade(UnidadeMemoria unidade) {
        return orElseNotFound(getRepository().findByRomUnidade(unidade));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchBySistemaOperacional(String OS) {
        return orElseNotFound(getRepository().findBySistemaOperacionalContainingIgnoreCase(OS));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> searchByLinguagem(String linguagem) {
        return orElseNotFound(getRepository().findByLinguagemEmbutidaContainingIgnoreCase(linguagem));
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

    protected abstract void applySpecificUpdates(Type current, Type incoming);
}