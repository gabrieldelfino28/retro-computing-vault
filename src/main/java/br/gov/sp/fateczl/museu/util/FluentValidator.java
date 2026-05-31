package br.gov.sp.fateczl.museu.util;

import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.ErrorInterface;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import br.gov.sp.fateczl.museu.util.logging.MuseumLogger;

import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * This Class is OPEN to edit. | Esta Classe está aberta a edição.
 */

public class FluentValidator {

    private FluentValidator() {}

    public static FluentValidator begin() {
        return new FluentValidator();
    }

    private Logger log() {
        return MuseumLogger.of(this.getClass());
    }

    public FluentValidator check(boolean condition, ErrorInterface err, Object... args) {
        if (condition) {
            log().err(LogMessage.VALIDATE_FAIL.errLogger(args));
            throw new BusinessRuleException(err, args);
        }
        return this;
    }

    public FluentValidator notNullObject(Object obj, ErrorInterface error, Object... args) {
        return check(obj == null, error, args);
    }

    public FluentValidator notEmpty(String value, ErrorInterface error, Object... args) {
        return check(value == null || value.isBlank(), error, args);
    }

    public FluentValidator notInFuture(LocalDate date, ErrorInterface error, Object... args) {
        return check(date != null && date.isAfter(LocalDate.now()), error, args);
    }

    public FluentValidator minYear(LocalDate date, int minYear, ErrorInterface error, Object... args) {
        return check(date != null && date.getYear() < minYear, error, args);
    }

    public FluentValidator limit(Collection<?> collection, int limit, ErrorInterface error, Object... args) {
        return check(collection != null && collection.size() > limit, error, args);
    }

    public FluentValidator isPositive(Number value, ErrorInterface error, Object... args) {
        return check(value != null && value.doubleValue() < 0, error, args);
    }

    public FluentValidator isGreaterThanZero(Number value, ErrorInterface error, Object... args) {
        return check(value != null && value.doubleValue() <= 0, error, args);
    }

    public <T> FluentValidator ifPresent(T value, Consumer<FluentValidator> validation) {
        if (value != null) {
            validation.accept(this);
        }
        return this;
    }

    public FluentValidator ifPresent(String value, Consumer<FluentValidator> validation) {
        if (value != null && !value.isBlank()) {
            validation.accept(this);
        }
        return this;
    }
}
