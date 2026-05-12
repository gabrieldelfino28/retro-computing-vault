package br.gov.sp.fateczl.museu.exception.codes;

import br.gov.sp.fateczl.museu.exception.ErrorInterface;

/**
 * This Class is OPEN to edit. | Esta Classe está aberta a edição.
 */

public enum HardwareErr implements ErrorInterface {

    PHOTO_LIMIT("O Objeto: '%s' excedeu o limite de '%d' fotos."),
    INVALID_DATE("A Data de '%s' não pode estar no futuro ou ser nulo."),
    ANO_INVALIDO("O Ano de '%s' deve ser superior a 1940 (Era da computação moderna)."),
    INVALID_ISO_CODE("O código de Moeda '%s' deve ser exatamente 3 caracteres (Padrão ISO)"),
    NEGATIVE_VALUE("O valor do Campo: '%s' não pode ser Negativo."),
    ZERO_VALUE("O valor do Campo: '%s' não pode ser Zero."),
    INVALID_CURRENCY("A moeda informada é inválida ou nula."),
    GENERATION_CONFLICT("A Geração '%s' não é compátivel com o ano '%d'."),
    REQUIRED_FIELD("O campo '%s' é obrigatório.");

    private final String message;

    HardwareErr(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String formatMessage(Object... args) {
        return String.format(message, args);
    }
}
