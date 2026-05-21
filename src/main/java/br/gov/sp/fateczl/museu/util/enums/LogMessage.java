package br.gov.sp.fateczl.museu.util.enums;

public enum LogMessage {
    // ─── CRUD ───
    RECORD("Iniciando registro do objeto: {}"),
    SAVE("Salvo com sucesso! ID: {}"),
    UPDATE("Iniciando update | ID: {}"),
    VALIDATE("Validando campos de {}"),
    LOAD("Carregando os atributos do objeto: {}"),
    DELETE("Excluído com sucesso! ID: {}"),

    // ─── Busca / Consulta ───
    FIND_BY_ID("Buscando por ID: {}"),
    FIND_ALL("Listando todos os registros de: {}"),
    FIND_BY_FILTER("Busca com filtro em {} | parâmetro: {}"),
    NOT_FOUND("Registro não encontrado | entidade: {} | ID: {}"),
    EMPTY_RESULT("Consulta retornou lista vazia para: {}"),

    // ─── Validação ───
    VALIDATE_START("Iniciando validação de campos: "),
    VALIDATE_FAIL("Falha na validação | campo: {} | valor: {}"),
    VALIDATE_OK("Validação concluída com sucesso: {}"),
    FIELD_NULL("Campo obrigatório nulo | campo: {} | entidade: {}"),
    FIELD_INVALID("Valor inválido | campo: {} | valor recebido: {}"),

    // ─── Relacionamentos ───
    RELATION_LINK("Associando {} ID: {} com {} ID: {}"),
    RELATION_UNLINK("Desassociando {} ID: {} de {} ID: {}"),
    RELATION_LINK_BATCH("Vinculando {} {} ao: {}"),
    RELATION_NOT_FOUND("Dependência não encontrada | {} ID: {}"),

    // ─── Auth / Segurança ───
    USR_LOGIN("Usuário autenticado: {}"),
    USR_LOGOUT("Usuário encerrou sessão: {}"),
    USR_LOGIN_FAIL("Tentativa de login falhou | usuário: {}"),
    ACCESS_DENIED("Acesso negado | usuário: {} | recurso: {}"),
    AUTH_ACCESS("Tentativa de acesso ao módulo admin por {}"),
    TOKEN_EXPIRED("Token expirado | usuário: {}"),
    PASSWORD_CHANGED("Senha alterada | usuário: {}"),

    // ─── Ciclo de vida / Sistema ───
    SERVICE_START("Serviço iniciado: "),
    SERVICE_STOP("Serviço encerrado: "),
    CACHE_HIT("Cache hit | chave: {}"),
    CACHE_MISS("Cache miss | chave: {}"),
    CACHE_EVICT("Cache invalidado | chave: {}"),

    // ─── Erros e Exceções ───
    UNEXPECTED_ERROR("Erro inesperado em {} | mensagem: {}"),
    DB_ERROR("Erro de banco de dados em {} | causa: {}"),
    INTEGRATION_ERROR("Falha na integração com {} | status: {}"),
    TIMEOUT("Timeout ao acessar {} | limite: {}ms"),

    // ─── Importação / Exportação ───
    IMPORT_START("Iniciando importação de: "),
    IMPORT_SUCCESS("Importação concluída | registros: {}"),
    IMPORT_FAIL("Falha na importação | linha: {} | causa: {}"),
    EXPORT_START("Iniciando exportação de: "),
    EXPORT_SUCCESS("Exportação concluída | arquivo: {}"),

    // ─── Auditoria ────
    AUDIT_CREATE("Criação registrada | entidade: {} | usuário: {}"),
    AUDIT_UPDATE("Alteração registrada | entidade: {} | ID: {} | usuário: {}"),
    AUDIT_DELETE("Exclusão registrada | entidade: {} | ID: {} | usuário: {}"),
    AUDIT_ACCESS("Acesso registrado | recurso: {} | usuário: {}")
    ;

    private final static String SEPARATOR = "█".repeat(6);
    private final String msg;

    LogMessage(String message) {
        this.msg = message;
    }

    /**
     * Formata a mensagem de log para uma entidade específica.
     * Os placeholders {@code {}} são resolvidos pelo SLF4J em ordem com os args passados no logger.
     *
     * @param entity Nome da entidade que aparecerá no log ex: "Museu", "Computador"
     * @return {@code ██████ [Entidade] Mensagem {} ██████}
     */
    public String forEntity(String entity) {
        return wrap(String.format("[%s] %s", entity, this.msg));
    }

    /**
     * Envolve uma mensagem avulsa com os separadores visuais do padrão de log.
     * Usar quando nenhum {@link LogMessage} encaixar na situação.
     *
     * @param message Mensagem livre com placeholders {@code {}} para o SLF4J
     * @return {@code ██████ Mensagem {} ██████}
     */
    public static String wrap(String message) {
        return SEPARATOR + message + SEPARATOR;
    }
}