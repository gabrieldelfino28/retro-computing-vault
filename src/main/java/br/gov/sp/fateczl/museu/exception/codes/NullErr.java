package br.gov.sp.fateczl.museu.exception.codes;

import br.gov.sp.fateczl.museu.exception.ErrorInterface;

/**
 * This Class is OPEN to edit. | Esta Classe está aberta a edição.
 */

public enum NullErr implements ErrorInterface {

    NULL_FIELD("O Campo: '%s', não pode ser nulo ou vazio."),
    NULL_OBJECT("O Objeto: '%s', não pode ser nulo."),
    INVALID_ID("O ID informado é inválido."),
    NOT_FOUND("Objeto(s) não escontrado(s) na busca.");

    private final String message;

    NullErr(String message) {
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
