package br.com.senac.urbanmap.controllers.dtos;

public record EstatisticaDTO(
        Long qtdLocais,
        Long qtdUsuarios,
        Long qtdCurtidas,
        Long qtdSalvos
) {

}
