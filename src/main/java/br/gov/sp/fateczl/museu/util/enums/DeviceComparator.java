package br.gov.sp.fateczl.museu.util.enums;

import br.gov.sp.fateczl.museu.domain.entity.Dispositivo;

import java.util.Comparator;

public enum DeviceComparator {

    RAM_ASC(Comparator.comparingLong(Dispositivo::getPesoRam)),

    RAM_DESC(Comparator.comparingLong(Dispositivo::getPesoRam).reversed()),

    ROM_ASC(Comparator.comparingLong(Dispositivo::getPesoRom)),

    ROM_DESC(Comparator.comparingLong(Dispositivo::getPesoRom).reversed()),

    RAM_ROM_ASC(Comparator.comparingLong(Dispositivo::getPesoRam)
            .thenComparingLong(Dispositivo::getPesoRom)),

    RAM_ROM_DESC(Comparator.comparingLong(Dispositivo::getPesoRam)
            .thenComparingLong(Dispositivo::getPesoRom).reversed());

    private final Comparator<? super Dispositivo> comparator;

    DeviceComparator(Comparator<? super Dispositivo> comparator) {
        this.comparator = comparator;
    }

    public Comparator<? super Dispositivo> get() {
        return this.comparator;
    }
}
