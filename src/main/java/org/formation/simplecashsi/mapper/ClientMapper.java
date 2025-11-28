package org.formation.simplecashsi.mapper;

import org.formation.simplecashsi.dto.ClientDto;
import org.formation.simplecashsi.dto.ClientCreateDto;
import org.formation.simplecashsi.entity.Client;
import org.formation.simplecashsi.entity.ClientUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface ClientMapper {

    ClientDto toDto(Client client);

    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // <-- Ajout CRUCIAL
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Client client, ClientUpdateDto dto);

    List<ClientDto> toDtoList(List<Client> clients);
}
