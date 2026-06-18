package it.pirelli.colloquiopieno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GestioneCorsoResponseDTO {

    private Long id;
    private CorsoDTO corso;
    private UtenteDTO user;

}
