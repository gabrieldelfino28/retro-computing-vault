package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;

import java.util.List;

public interface IHardwareService<T extends Hardware> {
    T insert(T hardware, List<Imagem> imgs);

    void update(T hardware);

    void deleteById(Long id);

    T searchById(Long id);

    List<T> getAll();
}
