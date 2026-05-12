package br.gov.sp.fateczl.museu.util.enums;

import lombok.Getter;

@Getter
public enum AppInfo {

    DEFAULT_OS("Sistema Operacional Embutido / ROM Resident."),
    DEFAULT_ROM("Configuração padrão de fábrica (ROM)."),
    NOT_INFORMED("Informação não fornecida."),
    UNKNOWN_VENDOR("Fabricante desconhecido."),
    UNKNOWN_ORIGIN("País de origem desconhecido."),
    
    ;

    private final String info;

    AppInfo(String value) {
        this.info = value;
    }
}
