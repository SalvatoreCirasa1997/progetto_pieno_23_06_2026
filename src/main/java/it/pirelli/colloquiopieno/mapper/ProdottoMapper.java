package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.ProdottoRequestDTO;
import it.pirelli.colloquiopieno.dto.ProdottoResponseDTO;
import it.pirelli.colloquiopieno.entity.Prodotto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface ProdottoMapper {

    @Mapping(target = "id", ignore = true)
    Prodotto toEntity(ProdottoRequestDTO prodottoRequestDTO);

    ProdottoResponseDTO toDto(Prodotto prodotto);

    List<Prodotto> toEntityList(List<ProdottoRequestDTO> prodottoRequestDTOList);

    List<ProdottoResponseDTO> toDtoList(List<Prodotto> prodottoList);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ProdottoRequestDTO prodottoRequestDTO, @MappingTarget Prodotto existingProdotto);
}
