package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.AulaRequestDTO;
import it.pirelli.colloquiopieno.dto.AulaResponseDTO;
import it.pirelli.colloquiopieno.entity.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface AulaMapper {

    @Mapping(target = "id", ignore = true)
    Aula toEntity(AulaRequestDTO dto);

    AulaResponseDTO toDto(Aula entity);

    List<Aula> toEntityList(List<AulaRequestDTO> dto);

    List<AulaResponseDTO> toDtoList(List<Aula> entity);

    @Mapping(target = "id", ignore = true)
    void updateEntity(AulaRequestDTO aulaRequestDTO, @MappingTarget Aula existingAula);
}
