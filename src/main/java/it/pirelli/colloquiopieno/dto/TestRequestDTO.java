package it.pirelli.colloquiopieno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestRequestDTO {

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 2, max = 100, message = "Il nome deve essere tra 2 e 100 caratteri")
    private String nome;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email deve essere valida")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, max = 255, message = "La password deve essere di almeno 8 caratteri")
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Il numero di telefono non è valido")
    @Size(max = 20, message = "Il telefono non può superare i 20 caratteri")
    private String cellulare;

    @Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri")
    private String indirizzo;

    @Size(max = 100, message = "La città non può superare i 100 caratteri")
    private String citta;
}