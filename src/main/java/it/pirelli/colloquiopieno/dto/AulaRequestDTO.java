package it.pirelli.colloquiopieno.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AulaRequestDTO {

    @NotBlank(message = "Il nome dell'aula non può essere vuoto")
    @Size(max = 50, message = "Il nome dell'aula non può superare i 50 caratteri")
    private String nome;

    @NotNull(message = "La capacità dell'aula non può essere nulla")
    private Integer capacita;


    private Integer piano;

    @NotBlank(message = "L'indirizzo dell'aula non può essere vuoto")
    @Size(max = 100, message = "L'indirizzo dell'aula non può superare i 100 caratteri")
    private String indirizzo;

    private Boolean hasProiettore = false;

    private Boolean hasLim = false;

    private List<CorsoResponseDTO> corsi = new ArrayList<>();
}
