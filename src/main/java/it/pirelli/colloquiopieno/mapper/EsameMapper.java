package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.EsameRequestDTO;
import it.pirelli.colloquiopieno.dto.EsameResponseDTO;
import it.pirelli.colloquiopieno.entity.Esame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface EsameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "materia", ignore = true)
    Esame toEntity(EsameRequestDTO dto);

    @Mapping(target = "materia", ignore = true)
    EsameResponseDTO toDto(Esame entity);

    List<EsameResponseDTO> toDto(List<Esame> entity);

    List<Esame> toEntityList(List<EsameRequestDTO> dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "materia", ignore = true)
    void updateEntity(EsameRequestDTO esameRequestDTO, @MappingTarget Esame existingEsame);
}
