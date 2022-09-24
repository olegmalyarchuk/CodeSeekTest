package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.persistence.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
  PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

  @Mapping(source = "team.id", target = "teamId")
  PlayerDTO toDTO(Player model);

  @Mapping(source = "teamId", target = "team.id")
  Player toModel(PlayerDTO dto);
}
