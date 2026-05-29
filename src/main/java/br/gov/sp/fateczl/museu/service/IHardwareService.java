package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IHardwareService<Type extends Hardware> {
    @Transactional
    Type insert(Type hardware, Set<Imagem> imagens);

    @Transactional
    void update(Type incoming);

    @Transactional(readOnly = true)
    List<Type> searchByModelo(String model);

    @Transactional(readOnly = true)
    List<Type> searchByFabricante(String fabricante);

    @Transactional(readOnly = true)
    List<Type> searchByDataLancamento(LocalDate data);

    @Transactional(readOnly = true)
    List<Type> searchByPais(String pais);

    @Transactional(readOnly = true)
    Type searchById(Long id);

    @Transactional(readOnly = true)
    List<Type> getAll();

    @Transactional
    void deleteById(Long id);
}
