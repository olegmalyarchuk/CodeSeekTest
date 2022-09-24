package com.example.footballmanager.service;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TeamDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerService {
  @Transactional
  PlayerDTO createPlayer(PlayerDTO playerDTO);

  PlayerDTO getPlayer(int id);

  PlayerDTO updatePlayer(int id, PlayerDTO playerDTO);

  void deletePlayer(int id);

  int calculatePlayersNumber();

  List<PlayerDTO> getPlayersByTeamId(int teamId);
}
