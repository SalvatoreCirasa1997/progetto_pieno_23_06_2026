package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.CorsoDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface CorsoMapper {

    @Mapping(target = "id", ignore = true)
    Corso toEntity(CorsoDTO dto);

    CorsoResponseDTO toDto(Corso corso);

    List<CorsoResponseDTO> toDto(List<Corso> corso);
}
