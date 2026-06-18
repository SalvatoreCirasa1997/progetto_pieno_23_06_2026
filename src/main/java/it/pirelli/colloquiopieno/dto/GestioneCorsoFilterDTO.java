package it.pirelli.colloquiopieno.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GestioneCorsoFilterDTO {

    private Long utenteId;
    private Long corsoId;

    @AssertTrue(message = "Deve essere valorizzato almeno utenteId o corsoId")
    public boolean isValid() {
        return utenteId != null || corsoId != null;
    }
}
