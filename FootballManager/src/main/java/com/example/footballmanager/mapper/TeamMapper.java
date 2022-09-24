package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.persistence.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {
  TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

  TeamDTO toDTO(Team model);

  Team toModel(TeamDTO dto);
}
