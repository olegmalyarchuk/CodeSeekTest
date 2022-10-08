package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.persistence.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface TeamMapper {
  TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

  TeamDTO toDTO(Team model);

  Team toModel(TeamDTO dto);
}
