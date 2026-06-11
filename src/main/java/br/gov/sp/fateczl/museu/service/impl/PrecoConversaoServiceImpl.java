package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.domain.entity.PrecoConversao;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.PrecoConversaoRepository;
import br.gov.sp.fateczl.museu.service.PrecoConversaoService;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrecoConversaoServiceImpl implements PrecoConversaoService {

    private final PrecoConversaoRepository repository;

    public PrecoConversaoServiceImpl(PrecoConversaoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public PrecoConversao insert(PrecoConversao entity) {
        checkFields(entity);
        return save(entity);
    }

    @Override
    @Transactional
    public void update(PrecoConversao incoming) {
        checkFields(incoming);

        var current = searchById(incoming.getId());
        current.setValorAtual(incoming.getValorAtual());
        current.setMoeda(incoming.getMoeda());
        current.setDataConversao(incoming.getDataConversao());
        current.setObservacao(incoming.getObservacao());
        current.setHardware(incoming.getHardware());
        save(current);
    }

    @Override
    @Transactional(readOnly = true)
    public PrecoConversao searchById(Integer id) {
        return orElseNotFound(repository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrecoConversao> getAll() {
        return orElseNotFound(repository.findAll());
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        var precoConv = orElseNotFound(repository.findById(id));
        log().info(LogMessage.DELETE, entity(),precoConv.getId());
        repository.delete(precoConv);
    }

    @Transactional(readOnly = true)
    @Override
    public PrecoConversao searchByHardwareId(Long id) {
        return orElseNotFound(repository.findByHardware_Id(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PrecoConversao> searchByData(LocalDate data){
        return orElseNotFound(repository.findByDataConversao(data));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PrecoConversao> searchByMoedaIso(String iso) {
        return orElseNotFound(repository.findByMoeda_Iso(iso));
    }

    private void checkFields(PrecoConversao p) {
        FluentValidator.begin().notNullObject(p, NullErr.NULL_OBJECT, entity());
        p.validate();
    }

    private PrecoConversao save(PrecoConversao p) {
        log().info(LogMessage.SAVE, entity(), p.getId());
        return repository.save(p);
    }

    @Override
    public String pluralEntity() {
        return entity().replace("Conversao", "Conversoes");
    }
}
