package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.exception.PlayerNotFoundException;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.persistence.dao.PlayerRepository;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  @Override
  @Transactional
  public PlayerDTO createPlayer(PlayerDTO playerDTO) {
    Player player = PlayerMapper.INSTANCE.toModel(playerDTO);
    player = playerRepository.save(player);
    log.info("PlayerService: create player {}", playerDTO);
    return PlayerMapper.INSTANCE.toDTO(player);
  }

  @Override
  public PlayerDTO getPlayer(int id) {
    log.info("PlayerService: get user by id {}", id);
    Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    return PlayerMapper.INSTANCE.toDTO(player);
  }

  @Override
  @Transactional
  public PlayerDTO updatePlayer(int id, PlayerDTO playerDTO) {
    Player player = PlayerMapper.INSTANCE.toModel(playerDTO);
    Player playerFromDB = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    playerRepository.delete(playerFromDB);
    player = playerRepository.save(player);
    log.info("PlayerService: update player with id {}", id);
    return PlayerMapper.INSTANCE.toDTO(player);
  }

  @Override
  public void deletePlayer(int id) {
    Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    playerRepository.delete(player);
    log.info("PlayerService: delete player with id {}", id);
  }

  @Override
  public int calculatePlayersNumber() {
    log.info("PlayerService: players count {}", playerRepository.calculatePlayersNumber());
    return playerRepository.calculatePlayersNumber();
  }

  @Override
  public List<PlayerDTO> getPlayersByTeamId(int teamId) {
    log.info("PlayerService: Get Reports by teamId {}" + teamId);
    List<Player> players = playerRepository.findAllByTeamId(teamId);
    List<PlayerDTO> playersDTO =
        players.stream().map(p -> PlayerMapper.INSTANCE.toDTO(p)).collect(Collectors.toList());
    return playersDTO;
  }
}
