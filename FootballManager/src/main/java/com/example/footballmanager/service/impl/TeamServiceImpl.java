package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.exception.PlayerNotFoundException;
import com.example.footballmanager.exception.TeamNotFoundException;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.mapper.TeamMapper;
import com.example.footballmanager.persistence.dao.PlayerRepository;
import com.example.footballmanager.persistence.dao.TeamRepository;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.persistence.model.Team;
import com.example.footballmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;
  private final PlayerRepository playerRepository;

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

  @Override
  public String transferPlayer(int sellerTeamId, int buyerTeamId, int playerId) {
    Team sellerTeam =
        teamRepository
            .findById(sellerTeamId)
            .orElseThrow(
                () ->
                    new TeamNotFoundException(
                        format("Team with id %d does not exist", sellerTeamId)));
    Team buyerTeam =
        teamRepository
            .findById(buyerTeamId)
            .orElseThrow(
                () ->
                    new TeamNotFoundException(
                        format("Team with id %d does not exist", buyerTeamId)));
    Player player =
        playerRepository
            .findById(playerId)
            .orElseThrow(
                () ->
                    new PlayerNotFoundException(
                        format("Player with id %d does not exist", playerId)));

    Integer totalPrice = calculateTotalPrice(player, buyerTeam);

    if (buyerTeam.getWallet() - totalPrice < 0) return "This player isn't affordable for you";

    sellerTeam.setWallet(sellerTeam.getWallet() + totalPrice);
    buyerTeam.setWallet(buyerTeam.getWallet() - totalPrice);
    player.setTeam(buyerTeam);

    playerRepository.save(player);
    teamRepository.save(sellerTeam);
    teamRepository.save(buyerTeam);
    return "The transfer was successful!";
  }

  private int calculateTotalPrice(Player player, Team buyerTeam) {
    Integer priceOfPlayer = player.getExperienceMonths() * 100000 / player.getAge();
    Integer commission = priceOfPlayer * buyerTeam.getCommisionTeam();
    return priceOfPlayer + commission;
  }
}
