package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DispositivoRepository<Type extends Dispositivo> extends HardwareRepository<Type> {

    List<Type> findByCpuContainingIgnoreCase(String cpu);

    List<Type> findByRamUnidade(UnidadeMemoria unidade);

    List<Type> findByRomUnidade(UnidadeMemoria unidade);

    List<Type> findBySistemaOperacionalContainingIgnoreCase(String sistemaOperacional);

    List<Type> findByLinguagemEmbutidaContainingIgnoreCase(String linguagemEmbutida);
}
