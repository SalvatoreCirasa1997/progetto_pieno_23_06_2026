package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.UtenteRequestDTO;
import it.pirelli.colloquiopieno.dto.UtenteResponseDTO;
import it.pirelli.colloquiopieno.entity.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface UtenteMapper {

    @Mapping(target = "id", ignore = true)
    Utente toEntity(UtenteRequestDTO utenteDto);

    UtenteResponseDTO toDto(Utente utente);

    List<UtenteResponseDTO> toDto(List<Utente> utenti);

    List<Utente> toEntity(List<UtenteRequestDTO> utentiDto);

    @Mapping(target = "id", ignore = true)
    void updateUtente(UtenteRequestDTO utenteRequestDTO,@MappingTarget Utente existingUtente);
}
