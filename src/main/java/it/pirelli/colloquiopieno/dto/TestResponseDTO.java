package it.pirelli.colloquiopieno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String password;
    private String cellulare;
    private String indirizzo;
    private String citta;
}
