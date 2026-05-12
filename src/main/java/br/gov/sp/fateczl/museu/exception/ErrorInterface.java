package br.gov.sp.fateczl.museu.exception;

public interface ErrorInterface {

    String getMessage();

    String formatMessage(Object... args);
}
