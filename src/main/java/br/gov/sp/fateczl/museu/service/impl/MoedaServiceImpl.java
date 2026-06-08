package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.Moeda;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.MoedaRepository;
import br.gov.sp.fateczl.museu.service.IService;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import br.gov.sp.fateczl.museu.util.service.ServiceSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoedaServiceImpl implements ServiceSupport, IService<Moeda, String> {

    private final MoedaRepository repository;

    public MoedaServiceImpl(MoedaRepository repository) {
        this.repository = repository;
    }

    public Moeda insert(Moeda m) {
        
        return save(m);
    }

    @Override
    public void update(Moeda incoming) {
        validateFields(incoming);

        var current = searchById(incoming.getIso());
        current.setInicio(incoming.getInicio());
        current.setFim(incoming.getFim());
        current.setSimbolo(incoming.getSimbolo());

    }

    @Override
    public Moeda searchById(String iso) {
        return orElseNotFound(repository.findById(iso));
    }

    @Override
    public List<Moeda> getAll() {
        return orElseNotFound(repository.findAll());
    }

    @Override
    public void deleteById(String iso) {
        var moeda = orElseNotFound(repository.findById(iso));
        log().info(LogMessage.DELETE, entity(), moeda.getIso());
        repository.deleteById(moeda.getIso());
    }

    private void validateFields(Moeda m) {
        FluentValidator.begin().notNullObject(m, NullErr.NULL_OBJECT, entity());
        m.validate();
    }

    private Moeda save(Moeda m) {
        log().info(LogMessage.SAVE, entity(), m.getIso());
        return repository.save(m);
    }
}
