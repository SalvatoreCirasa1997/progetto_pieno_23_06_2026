package it.pirelli.colloquiopieno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GestioneCorsoResponseDTO {

    private CorsoResponseDTO corso;
    private UtenteResponseDTO user;

}
