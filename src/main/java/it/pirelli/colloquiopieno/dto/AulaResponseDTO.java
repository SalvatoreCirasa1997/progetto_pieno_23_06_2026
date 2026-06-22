package it.pirelli.colloquiopieno.dto;

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
public class AulaResponseDTO {

    private Long id;
    private String nome;
    private Integer capacita;
    private Integer piano;
    private String indirizzo;
    private Boolean hasProiettore;
    private Boolean hasLim;
}
