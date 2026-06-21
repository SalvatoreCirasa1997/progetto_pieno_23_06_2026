package it.pirelli.colloquiopieno.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorsoRequestDTO {

    @NotBlank(message = "Il titolo non può essere vuoto")
    @Size(min = 2, max = 100, message = "Il titolo deve essere tra 2 e 100 caratteri")
    private String titolo;

    @Size(max = 250, message = "La descrizione non può superare i 250 caratteri")
    private String descrizione;

    @NotNull(message = "La data di inizio è obbligatoria")
    @Future(message = "La data di inizio non può essere precedente alla data di domani")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFine;

    private Integer numeroMassimoPartecipanti;

    @AssertTrue(message = "La data di inizio non può essere successiva alla data di fine")
    public boolean isDateRangeValid() {
        if (dataInizio == null || dataFine == null) {
            return true;
        }
        return !dataInizio.isAfter(dataFine);
    }

    @NotNull(message = "Id aula è obbligatorio")
    private Long idAula;

    @NotNull(message = "Id materia è obbligatorio")
    private Long idMateria;
}
