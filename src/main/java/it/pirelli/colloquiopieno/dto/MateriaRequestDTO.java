package it.pirelli.colloquiopieno.dto;

import it.pirelli.colloquiopieno.enums.TipoDurataEnum;
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
public class MateriaRequestDTO {

    @NotBlank(message = "Il nome della materia è obbligatorio")
    @Size(max = 100, message = "Il nome della materia non può superare i 100 caratteri")
    private String nome;

    @Size(max = 250, message = "La descrizione della materia non può superare i 250 caratteri")
    private String descrizione;

    @NotNull(message = "La durata della materia è obbligatoria")
    private Integer durata;

    @NotNull(message = "Il tipo di durata è obbligatorio")
    private TipoDurataEnum tipoDurata;

    private Integer cfu;
}
