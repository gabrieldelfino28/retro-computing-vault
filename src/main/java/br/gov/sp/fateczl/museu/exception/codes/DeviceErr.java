package br.gov.sp.fateczl.museu.exception.codes;

import br.gov.sp.fateczl.museu.exception.ErrorInterface;

public enum DeviceErr implements ErrorInterface {

    CPU_REQUIRED("A especificação da CPU é obrigatória para este dispositivo."),
    RAM_INVALID_QUANTITY("A quantidade de RAM deve ser um valor positivo."),
    RAM_UNIT_REQUIRED("A unidade de medida da RAM (MB, GB...) é obrigatória."),
    ROM_INVALID_QUANTITY("A quantidade de armazenamento (ROM) deve ser maior que zero."),
    ROM_UNIT_REQUIRED("A unidade de medida do armazenamento é obrigatória."),
    STORAGE_MEDIA_REQUIRED("A mídia de armazenamento (SSD, HD, Cartão) deve ser informada."),
    BUILTIN_LOGIC_REQUIRED("Para Dispositivos sem OS robutos, a Linguagem/BIOS embutida (ex: BASIC, Assembly Monitor) é obrigatória."),
    //OS_REQUIRED(""),
    INTERFACES_REQUIRED("As interfaces de Entrada/Saída são obrigatórias.");

    private final String message;

    DeviceErr(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String formatMessage(Object... args) {
        return String.format(message, args);
    }
}