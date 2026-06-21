package it.pirelli.colloquiopieno.dto;

import it.pirelli.colloquiopieno.enums.TipoDurataEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MateriaResponseDTO {

    private Long id;
    private String nome;
    private String descrizione;
    private Integer durata;
    private TipoDurataEnum tipoDurata;
    private Integer cfu;
    private List<CorsoResponseDTO> corsi;
    private List<EsameResponseDTO> esami;
}
