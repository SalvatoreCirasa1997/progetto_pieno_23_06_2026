package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.CorsoRequestDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.entity.Corso;
import it.pirelli.colloquiopieno.enums.StatoCorsoEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface CorsoMapper {

    @Mapping(target = "id", ignore = true)
    Corso toEntity(CorsoRequestDTO dto);

    CorsoResponseDTO toDto(Corso corso);

    List<CorsoResponseDTO> toDto(List<Corso> corso);

    @Mapping(target = "id", ignore = true)
    void updateCorso(CorsoRequestDTO corsoRequestDTO, @MappingTarget Corso existingCorso);

    @AfterMapping
    default void setStato(@MappingTarget Corso corso, CorsoRequestDTO dto) {

        if (dto.getDataInizio().isAfter(LocalDate.now())) {
            corso.setStato(StatoCorsoEnum.NON_ANCORA_INIZIATO);
        }
        else if (LocalDate.now().isBefore(dto.getDataFine()) && LocalDate.now().isAfter(dto.getDataInizio())) {
            corso.setStato(StatoCorsoEnum.IN_CORSO);
        }
        else {
            corso.setStato(StatoCorsoEnum.COMPLETATO);
        }
    }
}
