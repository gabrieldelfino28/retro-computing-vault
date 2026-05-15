package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.repository.HardwareRepository;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class HardwareServiceTemplate<T extends Hardware> implements IHardwareService<T> {

    @Transactional
    public final T insert(T hardware, List<Imagem> imagens) {
        log.info(Logger.RECORD.forEntity("Hardware"), hardware.getModelo());
        validateHardwareFields(hardware);

        if (imagens != null && !imagens.isEmpty()) {
            FluentValidator.begin().limit(imagens, 8, HardwareErr.PHOTO_LIMIT, "Imagens");
            log.info("Vinculando {} imagens ao hardware", imagens.size());
            imagens.forEach(hardware::addImagem);
        }
        return save(hardware);
    }

    private void validateHardwareFields(T h) {
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

    public final void update(T incoming) {
        log.info(Logger.UPDATE.forEntity("Hardware"), incoming.getId());
        T current = getRepository().findById(incoming.getId())
                .orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));

        applyHardwareUpdates(current, incoming);
        applyInheritedUpdates(current, incoming);

        save(current);
    }

    private void applyHardwareUpdates(T current, T incoming) {
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
    public final List<T> searchByModelo(String model) {
        List<T> res = getRepository().findByModeloContainingIgnoreCase(model);
        checkEmptyList(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final List<T> searchByFabricante(String fabricante) {
        List<T> res = getRepository().findByFabricanteContainingIgnoreCase(fabricante);
        checkEmptyList(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final List<T> searchByDataLancamento(LocalDate data) {
        List<T> res = getRepository().findByDataLancamento(data);
        checkEmptyList(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final List<T> searchByPais(String pais) {
        List<T> res = getRepository().findByPaisOrigemContainingIgnoreCase(pais);
        checkEmptyList(res);
        return res;
    }

    @Transactional(readOnly = true)
    public final T searchById(Long id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new BusinessRuleException(NullErr.NULL_FIELD));
    }

    @Transactional(readOnly = true)
    public final List<T> getAll() {
        return getRepository().findAll();
    }

    private void checkEmptyList(List<T> resultSet) {
        FluentValidator.begin().check(resultSet.isEmpty(), NullErr.NOT_FOUND);
    }

    /**
     * Interface de HardwareService
     */

    protected abstract HardwareRepository<T> getRepository();

    protected abstract void validateDeviceFields(T hardware);

    protected abstract T save(T hardware);

    protected abstract void applyInheritedUpdates(T current, T incoming);
}
