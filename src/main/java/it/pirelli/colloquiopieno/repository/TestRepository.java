package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Test;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    boolean existsByEmail(@NotBlank(message = "L'email è obbligatoria") @Email(message = "L'email deve essere valida") @Size(max = 150, message = "L'email non può superare i 150 caratteri") String email);
}
