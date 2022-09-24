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

  /**
   * This method implements the transfer of the player
   *
   * @param sellerTeamId team who sells the player
   * @param buyerTeamId team who buys the player
   * @param playerId player which will be transfered
   * @return comment
   */
  String transferPlayer(int sellerTeamId, int buyerTeamId, int playerId);
}
