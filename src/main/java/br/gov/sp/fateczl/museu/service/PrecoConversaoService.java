package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.PrecoConversao;
import br.gov.sp.fateczl.museu.util.service.ServiceSupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PrecoConversaoService extends ServiceSupport, BaseService<PrecoConversao, Integer> {
    @Override
    @Transactional
    PrecoConversao insert(PrecoConversao entity);

    @Override
    @Transactional
    void update(PrecoConversao incoming);

    @Override
    @Transactional(readOnly = true)
    PrecoConversao searchById(Integer id);

    @Override
    @Transactional(readOnly = true)
    List<PrecoConversao> getAll();

    @Override
    @Transactional
    void deleteById(Integer id);

    @Transactional(readOnly = true)
    PrecoConversao searchByHardwareId(Long id);

    @Transactional(readOnly = true)
    List<PrecoConversao> searchByData(LocalDate data);

    @Transactional(readOnly = true)
    List<PrecoConversao> searchByMoedaIso(String iso);
}
