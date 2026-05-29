package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import br.gov.sp.fateczl.museu.repository.DispositivoRepository;
import br.gov.sp.fateczl.museu.util.enums.DeviceComparator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DispositivoService<Type extends Dispositivo> extends IHardwareService<Type> {

    @Transactional(readOnly = true)
    List<Type> searchByCpu(String cpu);

    @Transactional(readOnly = true)
    List<Type> searchByRamUnidade(UnidadeMemoria unidade);

    @Transactional(readOnly = true)
    List<Type> searchByRomUnidade(UnidadeMemoria unidade);

    @Transactional(readOnly = true)
    List<Type> searchBySistemaOperacional(String OS);

    @Transactional(readOnly = true)
    List<Type> searchByLinguagem(String linguagem);

    @Transactional(readOnly = true)
    List<Type> searchByRamMinima(UnidadeMemoria unidade, Integer quantidade, DeviceComparator order);

    @Transactional(readOnly = true)
    List<Type> searchByRomMinima(UnidadeMemoria unidade, Integer quantidade, DeviceComparator order);
}
