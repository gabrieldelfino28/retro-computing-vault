package br.gov.sp.fateczl.museu.domain.enums;

import lombok.Getter;

@Getter
public enum TipoComputador {

    MAINFRAME("Mainframe", "Computadores de grande porte dedicados ao processamento crítico de dados em massa."),
    HOME_COMPUTER("Home Computer", "Computadores clássicos dos anos 80 (como a linha TK, Sinclair), geralmente conectados à TV."),
    MICROCOMPUTADOR("Microcomputador", "Computadores projetados para uso individual, categoria que deu origem ao PC moderno."),
    DESKTOP("Desktop", "Computadores de mesa projetados para uso fixo em estações de trabalho ou lazer."),
    NOTEBOOK("Notebook/Laptop", "Computadores portáteis que integram tela, teclado e hardware em uma única unidade móvel."),
    ESTACAO_DE_TRABALHO("Workstation", "Computadores de alto desempenho voltados para tarefas técnicas ou científicas pesadas."),
    SUPERCOMPUTADOR("Supercomputador", "Sistemas de altíssima performance para cálculos complexos e processamento paralelo intenso."),
    HANDHELD("Handheld/PDA", "Computadores de bolso históricos que antecederam os smartphones modernos."),
    SINGLE_BOARD_COMPUTER("SBC (Computador de Placa Única)", "Computadores completos construídos em uma única placa de circuito, como o Raspberry Pi."),
    WEARABLE("Wearable", "Dispositivos computacionais vestíveis, como smartwatches e óculos inteligentes."),
    SERVIDOR("Servidor", "Sistemas projetados para fornecer serviços, dados ou recursos a outros computadores em rede.");

    private final String nome;
    private final String descricao;

    /**
     * Construtor do Enum TipoComputador.
     * @param nome Nome amigável do tipo de hardware.
     * @param descricao Explicação detalhada da categoria para o museu.
     */
    TipoComputador(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
