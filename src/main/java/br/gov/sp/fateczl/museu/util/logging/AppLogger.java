package br.gov.sp.fateczl.museu.util.logging;


import br.gov.sp.fateczl.museu.util.Logger;
import br.gov.sp.fateczl.museu.util.enums.LogMessage;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

class AppLogger implements Logger {

    private final org.slf4j.Logger log;

    private final String separator = "";

    AppLogger(Class<?> clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void info(String message, Object... args) {
        log.info(LogMessage.wrap(message), args);
    }

    @Override
    public void info(LogMessage message, String entity) {
        log.info(message.forEntity(entity));
    }

    @Override
    public void info(LogMessage message, String entity, Object... args) {
        log.info(message.forEntity(entity), args);
    }

    @Override
    public void warn(String message, Object... args) {
        log.warn(LogMessage.wrap(message), args);
    }

    @Override
    public void warn(LogMessage message, String entity, Object... values) {
        log.warn(message.forEntity(entity), values);
    }

    @Override
    public void err(String message, Object... args) {
        log.error(LogMessage.wrap(message), args);
    }

    @Override
    public void err(LogMessage message, String entity, Throwable ex) {
        log.error(message.forEntity(entity), ex);
    }

    @Override
    public void err(LogMessage message, String entity, Throwable ex, Object... values) {
        log.error(message.forEntity(entity), ex, values);
    }

    @Override
    public void debug(String message, Object... args) {
        log.info(Arrays.toString(args));
    }

    @Override
    public void debug(LogMessage message, String entity, Object... values) {
        log.debug(message.forEntity(entity), values);
    }
}
