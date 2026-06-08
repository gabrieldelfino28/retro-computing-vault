package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.domain.enums.TipoComputador;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComputadorService extends DispositivoService<Computador> {

    @Transactional(readOnly = true)
    List<Computador> searchByTipo(TipoComputador tipo);

    @Transactional(readOnly = true)
    List<Computador> searchByResolucao(String res);
}