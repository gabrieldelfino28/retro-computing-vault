package br.gov.sp.fateczl.museu.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaseService<E, ID> {
    @Transactional
    E insert(E entity);

    @Transactional
    void update(E incoming);

    @Transactional(readOnly = true)
    E searchById(ID id);

    @Transactional(readOnly = true)
    List<E> getAll();

    @Transactional
    void deleteById(ID id);
}
