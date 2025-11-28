package org.formation.simplecashsi.mapper;

import org.formation.simplecashsi.dto.ClientDto;
import org.formation.simplecashsi.dto.ClientCreateDto;
import org.formation.simplecashsi.dto.ClientUpdateDto;
import org.formation.simplecashsi.entity.Client;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface ClientMapper {


    ClientDto toDto(Client client);

    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    void updateEntity(@MappingTarget Client client, ClientUpdateDto dto);

    List<ClientDto> toDtoList(List<Client> clients);
}
