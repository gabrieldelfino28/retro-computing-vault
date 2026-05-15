package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;

import java.time.LocalDate;
import java.util.List;

public interface IHardwareService<T extends Hardware> {
    T insert(T hardware, List<Imagem> imgs);

    void update(T hardware);

    void deleteById(Long id);

    T searchById(Long id);

    List<T> getAll();

    List<T> searchByModelo(String modelo);

    List<T> searchByFabricante(String fabricante);

    List<T> searchByDataLancamento(LocalDate dataLancamento);

    List<T> searchByPais(String paisOrigem);
}
