package br.gov.sp.fateczl.museu.domain.enums;

import lombok.Getter;

@Getter
public enum TipoConsole{

    MESA("Console de Mesa", "Sistemas projetados para serem conectados a uma televisão."),
    PORTATIL("Portátil", "Dispositivos autônomos com tela e bateria integrada."),
    HIBRIDO("Híbrido", "Sistemas que funcionam tanto como mesa quanto portátil."),
    MICRO_CONSOLE("Microconsole", "Dispositivos de baixo custo, geralmente baseados em Android."),
    PLUG_AND_PLAY("Plug & Play", "Sistemas com jogos na memória, sem suporte a cartuchos/discos."),
    DEDICADO("Dedicado", "Consoles que executam apenas o jogo (ou jogos) que vêm de fábrica.");

    private final String nome;
    private final String descricao;

    /**
     * Construtor do Enum TipoConsole.
     * @param nome Nome amigável do tipo de console.
     * @param descricao Explicação detalhada da categoria para o museu.
     */
    TipoConsole(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

}
