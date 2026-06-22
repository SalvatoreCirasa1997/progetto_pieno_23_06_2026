package it.pirelli.colloquiopieno.dto;

import it.pirelli.colloquiopieno.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdottoResponseDTO {

    private Long id;
    private String nome;
    private CategoriaEnum categoria;
    private Double prezzo;
    private Integer disponibilita;
    private String descrizione;
}
