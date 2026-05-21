package br.gov.sp.fateczl.museu.exception;

import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException{

    private final ErrorInterface errInterface;

    public BusinessRuleException(ErrorInterface errInterface, Object... args) {
        super(errInterface.getFormatedMessage(args));
        this.errInterface = errInterface;
    }
}
