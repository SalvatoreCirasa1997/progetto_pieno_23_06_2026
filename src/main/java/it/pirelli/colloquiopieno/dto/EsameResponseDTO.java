package it.pirelli.colloquiopieno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EsameResponseDTO {

    private Long id;
    private String nome;
    private LocalDateTime data;
    private String descrizione;
    private MateriaResponseDTO materia;
}
