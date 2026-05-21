package br.gov.sp.fateczl.museu.util;

import br.gov.sp.fateczl.museu.util.enums.LogMessage;

public interface Logger {

    void info(String message, Object... args);
    void info(LogMessage message, String entity);
    void info(LogMessage message, String entity, Object... args);

    void warn(String message, Object... args);
    void warn(LogMessage message, String entity, Object... args);

    void err(String message, Object...args);
    void err(LogMessage message, String entity, Throwable ex);
    void err(LogMessage message, String entity, Throwable ex, Object... args);

    void debug (String message, Object... args);
    void debug(LogMessage message, String entity, Object... args);
}
