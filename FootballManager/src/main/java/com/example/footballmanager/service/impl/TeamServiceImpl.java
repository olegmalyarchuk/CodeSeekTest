package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.exception.PlayerNotFoundException;
import com.example.footballmanager.exception.TeamNotFoundException;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.mapper.TeamMapper;
import com.example.footballmanager.persistence.dao.TeamRepository;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.persistence.model.Team;
import com.example.footballmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;

  @Override
  public TeamDTO createTeam(TeamDTO teamDTO) {
    Team team = TeamMapper.INSTANCE.toModel(teamDTO);
    team = teamRepository.save(team);
    log.info("TeamService: create team {}", teamDTO);
    return TeamMapper.INSTANCE.toDTO(team);
  }

  @Override
  public TeamDTO getTeam(int id) {
    log.info("TeamService: get team by id {}", id);
    Team team = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    return TeamMapper.INSTANCE.toDTO(team);
  }

  @Override
  public TeamDTO updateTeam(int id, TeamDTO teamDTO) {
    Team team = TeamMapper.INSTANCE.toModel(teamDTO);
    Team teamFromDB = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    teamRepository.delete(teamFromDB);
    team = teamRepository.save(team);
    log.info("TeamService: update team with id {}", id);
    return TeamMapper.INSTANCE.toDTO(team);
  }

  @Override
  public void deleteTeam(int id) {
    Team team = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    teamRepository.delete(team);
    log.info("TeamService: delete team with id {}", id);
  }

  @Override
  public int calculateTeamsNumber() {
    log.info("TeamService: teams count {}", teamRepository.calculateTeamsNumber());
    return teamRepository.calculateTeamsNumber();
  }
}
