package br.gov.sp.fateczl.museu.util.enums;

public enum Logger {

    RECORD("Iniciando registro do objeto: "),
    SAVE("Salvo com sucesso! ID: "),
    UPDATE("Iniciando update | ID: "),
    VALIDATE("Validando campos de "),
    LOAD("Carregando os atributos do objeto: "),
    USR_LOGIN("Usuário autenticado: {}"),
    DELETE("Excluído com sucesso! ID: "),
    AUTH_ACCESS("Tentativa de acesso ao módulo admin por {}")
    ;

    private final static String separator = "█".repeat(6);
    private final String msg;

    Logger(String message) {
        this.msg = message;
    }

    public String forEntity(String entity) {
     // return: ██████ [Entidade] Mensagem {} ██████
        return separator + String.format("[%s] %s {}", entity, this.msg) + separator;
    }
}