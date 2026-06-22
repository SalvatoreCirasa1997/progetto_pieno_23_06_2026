package it.pirelli.colloquiopieno.dto;

import it.pirelli.colloquiopieno.enums.CategoriaEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdottoRequestDTO {

    @NotBlank(message = "il nome non può essere vuoto")
    private String nome;

    private CategoriaEnum categoria;

    @NotNull(message = "il prezzo non può essere vuoto")
    private Double prezzo;

    @NotNull(message = "la disponibilità non può essere vuota")
    @Min(value = 0, message = "la disponibilità non può essere negativa")
    private Integer disponibilita;

    @Size(max = 100, message = "la descrizione non può superare 100 caratteri")
    private String descrizione;
}
