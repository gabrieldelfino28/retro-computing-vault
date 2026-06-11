package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.Moeda;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.MoedaRepository;
import br.gov.sp.fateczl.museu.service.MoedaService;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Service
public class MoedaServiceImpl implements MoedaService {

    private final MoedaRepository repository;

    public MoedaServiceImpl(MoedaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Moeda insert(Moeda m) {
        checkFields(m);
        return save(m);
    }

    @Override
    @Transactional
    public void update(Moeda incoming) {
        checkFields(incoming);

        var current = searchById(incoming.getIso());
        current.setInicio(incoming.getInicio());
        current.setFim(incoming.getFim());
        current.setSimbolo(incoming.getSimbolo());
        save(current);
    }

    @Override
    @Transactional(readOnly = true)
    public Moeda searchById(String iso) {
        return orElseNotFound(repository.findByIsoContainingIgnoreCase(iso));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Moeda> getAll() {
        return orElseNotFound(repository.findAll());
    }

    @Override
    @Transactional
    public void deleteById(String iso) {
        var moeda = orElseNotFound(repository.findById(iso));
        log().info(LogMessage.DELETE, entity(), moeda.getIso());
        repository.deleteById(moeda.getIso());
    }

    @Transactional(readOnly = true)
    @Override
    public Moeda searchBySimbolo(String simbolo) {
        return orElseNotFound(repository.findBySimboloContainingIgnoreCase(simbolo));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Moeda> searchByInicio(Year inicio) {
        return orElseNotFound(repository.findByInicio(inicio));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Moeda> searchByFim(Year fim) {
        return orElseNotFound(repository.findByFim(fim));
    }

    private void checkFields(Moeda m) {
        FluentValidator.begin().notNullObject(m, NullErr.NULL_OBJECT, entity());
        m.validate();
    }

    private Moeda save(Moeda m) {
        log().info(LogMessage.SAVE, entity(), m.getIso());
        return repository.save(m);
    }

    @Override
    public String pluralEntity() {
        return entity() + "s";
    }
}
