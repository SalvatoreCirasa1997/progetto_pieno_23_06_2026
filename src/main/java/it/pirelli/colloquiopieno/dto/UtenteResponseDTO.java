package it.pirelli.colloquiopieno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtenteResponseDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String cf;
}
