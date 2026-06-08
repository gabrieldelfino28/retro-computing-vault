package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import br.gov.sp.fateczl.museu.util.service.ServiceSupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface IHardwareService<Type extends Hardware> extends IService<Type, Long>, ServiceSupport {
    @Transactional
    Type insert(Type hardware, Set<Imagem> imagens);

    @Override
    @Transactional
    default Type insert(Type entity) {
        return insert(entity, Collections.emptySet());
    }

    @Transactional(readOnly = true)
    List<Type> searchByModelo(String model);

    @Transactional(readOnly = true)
    List<Type> searchByFabricante(String fabricante);

    @Transactional(readOnly = true)
    List<Type> searchByDataLancamento(LocalDate data);

    @Transactional(readOnly = true)
    List<Type> searchByPais(String pais);

}
