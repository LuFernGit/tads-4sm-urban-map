package br.com.senac.urbanmap.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalAlteracaoDTO(
        @NotNull(message = "ID é obrigatorio") Long id,
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Descrição é obrigatorio") String descricao,
        @NotBlank(message = "Endereço é obrigatório") String endereco,
        @NotBlank(message = "Cidade é obrigatório") String cidade,
        @NotBlank(message = "Estado é obrigatório") String estado,
        @NotBlank(message = "Cep é obrigatório") String cep,
        @NotNull(message = "Latitude é obrigatório") BigDecimal latitude,
        @NotNull(message = "Longitude é obrigatorio") BigDecimal longitude,
        List<String> urls,
        Set<Long> tagsId
) {
}