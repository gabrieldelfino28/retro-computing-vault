package br.gov.sp.fateczl.museu.domain.enums;

import lombok.Getter;

@Getter
public enum UnidadeMemoria {

    BIT("Bits", "Bit", 1L),
    B("Bytes", "Byte", 8L),
    KB("Kilobytes", "Kilobyte", 8L * 1024),
    MB("Megabytes", "Megabyte", 8L * 1024 * 1024),
    GB("Gigabytes", "Gigabyte", 8L * 1024 * 1024 * 1024);

    private final String nomePlural;
    private final String nomeSingular;
    private final long pesoBits;

    UnidadeMemoria(String plural, String singular, long pesoBits) {
        this.nomePlural = plural;
        this.nomeSingular = singular;
        this.pesoBits = pesoBits;
    }

    public String getDescricaoByQuantity(Integer memoriaQuatidade) {
        if (memoriaQuatidade == null || memoriaQuatidade < 1) return this.nomeSingular;
        return this.nomePlural;
    }
}
