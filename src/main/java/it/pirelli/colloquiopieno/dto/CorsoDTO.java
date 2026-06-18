package it.pirelli.colloquiopieno.dto;

import it.pirelli.colloquiopieno.enums.StatoCorsoEnum;
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
public class CorsoDTO {

    @NotBlank(message = "Il titolo non può essere vuoto")
    @Size(min = 2, max = 100, message = "Il titolo deve essere tra 2 e 100 caratteri")
    private String titolo;

    @Size(max = 250, message = "La descrizione non può superare i 250 caratteri")
    private String descrizione;

    @NotNull(message = "La data di inizio è obbligatoria")
    @FutureOrPresent(message = "La data di inizio non può essere precedente a oggi")
    private LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    private LocalDate dataFine;

    private Integer numeroMassimoPartecipanti;

    @AssertTrue(message = "La data di inizio non può essere successiva alla data di fine")
    public boolean isDateRangeValid() {
        if (dataInizio == null || dataFine == null) {
            return true;
        }
        return !dataInizio.isAfter(dataFine);
    }
}
