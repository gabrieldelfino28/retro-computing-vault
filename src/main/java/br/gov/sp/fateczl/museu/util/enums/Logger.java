package br.gov.sp.fateczl.museu.util.enums;

public enum Logger {

    BEGIN("Iniciando registro do objeto: "),
    END("Salvo com sucesso! ID: "),
    VALIDATE("Validando campos de "),
    LOAD_START(""),
    USR_LOGIN("Usuário autenticado: {}"),
    AUTH_ACCESS("Tentativa de acesso ao módulo admin por {}")
    ;

    private final String msg;

    Logger(String message) {
        this.msg = message;
    }

    public String forEntity(String entity) {
     // return: [Entidade] Mensagem {}
        return String.format("[%s] %s {}", entity, this.msg);
    }
}
