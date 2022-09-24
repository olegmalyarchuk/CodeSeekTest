package com.example.footballmanager.service;

import com.example.footballmanager.dto.TeamDTO;
import org.springframework.transaction.annotation.Transactional;

public interface TeamService {
  @Transactional
  TeamDTO createTeam(TeamDTO teamDTO);

  TeamDTO getTeam(int id);

  TeamDTO updateTeam(int id, TeamDTO teamDTO);

  void deleteTeam(int id);

  int calculateTeamsNumber();
}
