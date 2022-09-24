package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.persistence.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamListMapper {
  TeamListMapper INSTANCE = Mappers.getMapper(TeamListMapper.class);

  List<Team> toModelList(List<TeamDTO> dtos);

  List<TeamDTO> toDTOList(List<Team> models);
}
