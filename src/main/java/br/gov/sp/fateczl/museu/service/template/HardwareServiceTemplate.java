package br.gov.sp.fateczl.museu.service.template;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.HardwareRepository;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.Logger;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import br.gov.sp.fateczl.museu.util.logging.MuseumLogger;
import lombok.extern.java.Log;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class HardwareServiceTemplate<Type extends Hardware, Repository extends HardwareRepository<Type>> {
    
    protected final Logger log() {
        return MuseumLogger.of(this.getClass());
    }

    @Transactional
    public final Type insert(Type hardware, Set<Imagem> imagens) {
        log().info(LogMessage.RECORD, "Hardware", hardware.getModelo());
        validateHardwareFields(hardware);

        if (imagens != null && !imagens.isEmpty()) {
            FluentValidator.begin().limit(imagens, 8, HardwareErr.PHOTO_LIMIT, "Imagens");
            log().info(LogMessage.RELATION_LINK_BATCH, "Hardware", imagens.size(), "Imagens", hardware.getModelo());
            imagens.forEach(hardware::addImagem);
        }
        return save(hardware);
    }

    private void validateHardwareFields(Type h) {
        FluentValidator.begin()
                .notNullObject(h, NullErr.NULL_OBJECT, "Hardware")

                .notEmpty(h.getModelo(), HardwareErr.REQUIRED_FIELD, "modelo")
                .notEmpty(h.getFabricante(), HardwareErr.REQUIRED_FIELD, "fabricante")
                .notEmpty(h.getDescricao(), HardwareErr.REQUIRED_FIELD, "descrição")
                .notEmpty(h.getPaisOrigem(), HardwareErr.REQUIRED_FIELD, "país_origem")
                .notEmpty(h.getLinhaProduto(), HardwareErr.REQUIRED_FIELD, "linha_produto")

                .notNullObject(h.getDataLancamento(), HardwareErr.REQUIRED_FIELD, "data de lançamento")
                .notInFuture(h.getDataLancamento(), HardwareErr.INVALID_DATE, "data de lançamento")
                .minYear(h.getDataLancamento(), 1940, HardwareErr.ANO_INVALIDO, "data de lançamento")

                .notNullObject(h.getValorOriginal(), HardwareErr.REQUIRED_FIELD, "valor original")
                .isPositive(h.getValorOriginal(), HardwareErr.NEGATIVE_VALUE, "valor original")
                .notNullObject(h.getMoedaISO(), NullErr.NULL_OBJECT, "Moeda")
                .notNullObject(h.getRegistradoPor(), NullErr.NULL_OBJECT, "Usuário")
        ;
        validateDeviceFields(h);
    }

    public final void update(Type incoming) {
        log().info(LogMessage.UPDATE, "Hardware", incoming.getId());
        Type current = getRepository().findById(incoming.getId())
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
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

    @Transactional(readOnly = true)
    public final List<Type> searchByModelo(String model) {
        List<Type> set = getRepository().findByModeloContainingIgnoreCase(model);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    public final List<Type> searchByFabricante(String fabricante) {
        List<Type> set = getRepository().findByFabricanteContainingIgnoreCase(fabricante);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    public final List<Type> searchByDataLancamento(LocalDate data) {
        List<Type> set = getRepository().findByDataLancamento(data);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    public final List<Type> searchByPais(String pais) {
        List<Type> set = getRepository().findByPaisOrigemContainingIgnoreCase(pais);
        checkEmptyList(set);
        return set;
    }

    @Transactional(readOnly = true)
    public final Type searchById(Long id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NULL_FIELD));
    }

    @Transactional(readOnly = true)
    public final List<Type> getAll() {
        return getRepository().findAll();
    }

    protected void checkEmptyList(List<Type> resultSet) {
        FluentValidator.begin().check(resultSet.isEmpty(), NullErr.NOT_FOUND);
    }

    /**
     * @implNote | Interface Abstrata de HardwareService
     */

    protected abstract Repository getRepository();

    protected abstract void validateDeviceFields(Type hardware);

    protected abstract Type save(Type hardware);

    protected abstract void applyInheritedUpdates(Type current, Type incoming);

    public abstract void deleteById(Long id);
}
