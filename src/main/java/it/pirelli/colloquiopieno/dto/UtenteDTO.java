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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtenteDTO {

    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il nome deve essere tra 2 e 50 caratteri")
    private String nome;

    @NotBlank(message = "Il cognome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il cognome deve essere tra 2 e 50 caratteri")
    private String cognome;

    @NotBlank
    @Email(message = "Formato email non valido")
    private String email;

    @Size(min = 2, max = 100, message = "L'indirizzo deve essere tra 2 e 100 caratteri")
    private String indirizzo;

    @NotBlank
    @Size(min = 16, max = 16)
    @Pattern(
            regexp = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$",
            message = "Codice fiscale non valido"
    )
    private String cf;
}
