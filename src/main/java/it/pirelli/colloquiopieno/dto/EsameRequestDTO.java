package it.pirelli.colloquiopieno.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EsameRequestDTO {

    @NotBlank(message = "Il nome dell'esame è obbligatorio")
    @Size(max = 100, message = "Il nome dell'esame non può superare i 100 caratteri")
    private String nome;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;

    private String descrizione;

    @NotNull(message = "L'id della materia è obbligatorio")
    private Long idMateria;
}
