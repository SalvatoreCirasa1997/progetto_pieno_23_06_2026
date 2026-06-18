package it.pirelli.colloquiopieno.dto;

import it.pirelli.colloquiopieno.enums.StatoCorsoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorsoResponseDTO {

    private Long id;
    private String titolo;
    private String descrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Integer numeroMassimoPartecipanti;
    private StatoCorsoEnum stato;
}
