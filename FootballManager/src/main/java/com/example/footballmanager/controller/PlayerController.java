package com.example.footballmanager.controller;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.validation.BasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
@Api(value = "players", description = "Operations related to players in FootballManager Program")
public class PlayerController {
  private final PlayerService playerService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  @ApiOperation(value = "Create new player")
  public PlayerDTO createPlayer(@Validated(BasicInfo.class) @RequestBody PlayerDTO playerDTO) {
    log.info("creating player {}", playerDTO);
    return playerService.createPlayer(playerDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Get player by id")
  public PlayerDTO getPlayer(@PathVariable int id) {
    System.out.println("controller " + id);
    log.info("Getting player with id {}", id);
    return playerService.getPlayer(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Update player by id")
  public PlayerDTO updatePlayer(@PathVariable int id, @RequestBody PlayerDTO playerDTO) {
    log.info("Updating player with id {}", id);
    return playerService.updatePlayer(id, playerDTO);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete player by id")
  public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
    log.info("Deleting player with id {}", id);
    playerService.deletePlayer(id);
    return ResponseEntity.noContent().build();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/count")
  @ApiOperation(value = "Show count of players")
  public int calculatePlayersNumber() {
    log.info("Calculating players number{}", playerService.calculatePlayersNumber());
    return playerService.calculatePlayersNumber();
  }

  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get all players by teamId")
  @GetMapping(value = "/getByTeamId/{teamId}")
  public List<PlayerDTO> getPlayersByTeamId(@PathVariable int teamId) {
    log.info("Getting players by teamId {}", teamId);
    return playerService.getPlayersByTeamId(teamId);
  }
}
