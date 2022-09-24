package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.persistence.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
  PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

  PlayerDTO toDTO(Player model);

  Player toModel(PlayerDTO dto);
}
