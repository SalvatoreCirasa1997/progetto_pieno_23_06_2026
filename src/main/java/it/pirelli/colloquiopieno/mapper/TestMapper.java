package it.pirelli.colloquiopieno.mapper;

import it.pirelli.colloquiopieno.dto.TestDTO;
import it.pirelli.colloquiopieno.entity.Test;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "version", ignore = true)
    Test toEntity(TestDTO testDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "cellulare", source = "cellulare")
    @Mapping(target = "indirizzo", source = "indirizzo")
    @Mapping(target = "citta", source = "citta")
    TestDTO toDto(Test test);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(TestDTO testDTO, @MappingTarget Test test);

    List<TestDTO> toDtoList(List<Test> tests);

    List<Test> toEntityList(List<TestDTO> testDTOs);
}