package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.MateriaRequestDTO;
import it.pirelli.colloquiopieno.dto.MateriaResponseDTO;
import it.pirelli.colloquiopieno.entity.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface MateriaMapper {

    @Mapping(target = "id", ignore = true)
    Materia toEntity(MateriaRequestDTO materiaRequestDTO);

    MateriaResponseDTO toDto(Materia materia);

    List<MateriaResponseDTO> toDto(List<Materia> materia);

    List<Materia>  toEntity(List<MateriaRequestDTO> materiaRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(MateriaRequestDTO materiaRequestDTO, @MappingTarget Materia materia);
}
