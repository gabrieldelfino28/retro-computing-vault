package br.gov.sp.fateczl.museu.util.logging;

import br.gov.sp.fateczl.museu.util.Logger;

/**
 * This is the Factory for AppLogger
 * @see AppLogger
 */
public final class MuseumLogger {

    private MuseumLogger() {}

    public static Logger of(Class<?> type) {
        return new AppLogger(type);
    }
}
