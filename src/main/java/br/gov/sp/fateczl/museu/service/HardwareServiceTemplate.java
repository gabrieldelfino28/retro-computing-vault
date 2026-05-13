package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import br.gov.sp.fateczl.museu.exception.codes.HardwareErr;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.enums.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public abstract class HardwareServiceTemplate<T extends Hardware> {

    @Transactional
    public final T register(T hardware, List<Imagem> imagens) {
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

    protected abstract void validateDeviceFields(T hardware);

    protected abstract T save(T hardware);

    public abstract void update(T hardware);

    public abstract void deleteById(Long id);
}
