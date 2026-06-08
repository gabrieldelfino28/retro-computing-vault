package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Moeda;
import br.gov.sp.fateczl.museu.util.service.ServiceSupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

public interface MoedaService extends IService<Moeda, String>, ServiceSupport {
    @Transactional
    Moeda insert(Moeda m);

    @Override
    @Transactional
    void update(Moeda incoming);

    @Override
    @Transactional(readOnly = true)
    Moeda searchById(String iso);

    @Override
    @Transactional(readOnly = true)
    List<Moeda> getAll();

    @Override
    @Transactional
    void deleteById(String iso);

    @Transactional(readOnly = true)
    Moeda searchBySimbolo(String simbolo);

    @Transactional(readOnly = true)
    List<Moeda> searchByInicio(Year inicio);

    @Transactional(readOnly = true)
    List<Moeda> searchByFim(Year fim);
}
