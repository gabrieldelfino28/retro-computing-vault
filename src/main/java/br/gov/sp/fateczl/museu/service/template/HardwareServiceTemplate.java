package br.gov.sp.fateczl.museu.service.template;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.HardwareRepository;
import br.gov.sp.fateczl.museu.service.IHardwareService;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import br.gov.sp.fateczl.museu.util.service.ServiceSupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class HardwareServiceTemplate
        <Type extends Hardware, Repository extends HardwareRepository<Type>>
        implements IHardwareService<Type>, ServiceSupport {

    @Override
    @Transactional
    public Type insert(Type hardware, Set<Imagem> imgs) {
        log().info(LogMessage.RECORD, entity(), hardware.getModelo());
        validateHardwareFields(hardware);
        validateBusinessRules(hardware);

        if (imgs != null && !imgs.isEmpty()) {
            FluentValidator.begin().limit(imgs, 8, HardwareErr.PHOTO_LIMIT, collection(imgs));
            log().info(LogMessage.RELATION_LINK_BATCH, entity(), imgs.size(), collection(imgs), hardware.getModelo());
            imgs.forEach(hardware::addImagem);
        }
        return save(hardware);
    }

    private void validateHardwareFields(Type h) {
        FluentValidator.begin().notNullObject(h, NullErr.NULL_OBJECT, entity());
        h.validate();
    }

    @Override
    @Transactional
    public void update(Type incoming) {
        log().info(LogMessage.UPDATE, entity(), incoming.getId());
        Type current = orElseNotFound(getRepository().findById(incoming.getId()));
        applyHardwareUpdates(current, incoming);
        applyInheritedUpdates(current, incoming);
        save(current);
    }

    private void applyHardwareUpdates(Type current, Type incoming) {
        current.setModelo(incoming.getModelo());
        current.setFabricante(incoming.getFabricante());
        current.setDescricao(incoming.getDescricao());
        current.setDataLancamento(incoming.getDataLancamento());
        current.setPaisOrigem(incoming.getPaisOrigem());
        current.setObservacao(incoming.getObservacao());
        current.setLinhaProduto(incoming.getLinhaProduto());
        current.setValorOriginal(incoming.getValorOriginal());
        current.setMoedaISO(incoming.getMoedaISO());
        current.setImagens(incoming.getImagens());
    }

    /**
     * HardwareRepository searchBy methods
     */

    @Override
    @Transactional(readOnly = true)
    public List<Type> searchByModelo(String model) {
        return orElseNotFound(getRepository().findByModeloContainingIgnoreCase(model));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Type> searchByFabricante(String fabricante) {
        return orElseNotFound(getRepository().findByFabricanteContainingIgnoreCase(fabricante));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Type> searchByDataLancamento(LocalDate data) {
        return orElseNotFound(getRepository().findByDataLancamento(data));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Type> searchByPais(String pais) {
        return orElseNotFound(getRepository().findByPaisOrigemContainingIgnoreCase(pais));
    }

    @Override
    @Transactional(readOnly = true)
    public Type searchById(Long id) {
        return orElseNotFound(getRepository().findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Type> getAll() {
        return orElseNotFound(getRepository().findAll());
    }

//    protected void checkEmptyList(List<Type> resultSet) {
//        FluentValidator.begin().check(resultSet.isEmpty(), NullErr.NOT_FOUND);
//    }

    /**
     * @implNote | Interface Abstrata de HardwareService
     */

    protected abstract Repository getRepository();

    protected abstract void validateBusinessRules(Type hardware);

    protected abstract Type save(Type hardware);

    protected abstract void applyInheritedUpdates(Type current, Type incoming);

}
