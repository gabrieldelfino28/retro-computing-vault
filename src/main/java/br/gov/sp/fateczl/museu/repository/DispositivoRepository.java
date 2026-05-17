package br.gov.sp.fateczl.museu.repository;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;
import br.gov.sp.fateczl.museu.domain.enums.UnidadeMemoria;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DispositivoRepository<T extends Dispositivo> extends HardwareRepository<T> {

    List<T> findByCpuContainingIgnoreCase(String cpu);

    List<T> findByRamUnidade(UnidadeMemoria unidade);
    List<T> findByRamUnidadeOrderByRamQuantidade(UnidadeMemoria unidade);
    List<T> findByRamUnidadeAndByRamQuantidade(UnidadeMemoria unidade, Integer quantidade);

    List<T> findByRomUnidade(UnidadeMemoria unidade);
    List<T> findByRomUnidadeAndByRomQuantidade(UnidadeMemoria unidade, Integer quantidade);


    List<T> findBySistemaOperacionalContainingIgnoreCase(String sistemaOperacional);

    List<T> findByLinguagemEmbutidaContainingIgnoreCase(String linguagemEmbutida);
}
