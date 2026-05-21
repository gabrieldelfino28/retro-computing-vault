package br.gov.sp.fateczl.museu.util.logging;

import br.gov.sp.fateczl.museu.util.Logger;

public final class MuseumLogger {

    private MuseumLogger() {}

    public static Logger of(Class<?> clazz) {
        return new AppLogger(clazz);
    }
}
