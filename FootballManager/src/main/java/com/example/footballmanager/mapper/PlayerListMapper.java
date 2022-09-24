package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.persistence.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerListMapper {
  PlayerListMapper INSTANCE = Mappers.getMapper(PlayerListMapper.class);

  List<Player> toModelList(List<PlayerDTO> dtos);

  List<PlayerDTO> toDTOList(List<Player> models);
}
