package com.example.footballmanager.controller;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.validation.BasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@Api(value = "teams", description = "Operations related to teams in FootballManager Program")
public class TeamController {
  private final TeamService teamService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  @ApiOperation(value = "Create new team")
  public TeamDTO createTeam(@Validated(BasicInfo.class) @RequestBody TeamDTO teamDTO) {
    log.info("creating team {}", teamDTO);
    return teamService.createTeam(teamDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Get team by id")
  public TeamDTO getTeam(@PathVariable int id) {
    log.info("Getting team with id {}", id);
    return teamService.getTeam(id);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete team by id")
  public ResponseEntity<Void> deleteTeam(@PathVariable int id) {
    log.info("Deleting team with id {}", id);
    teamService.deleteTeam(id);
    return ResponseEntity.noContent().build();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/count")
  @ApiOperation(value = "Show count of teams")
  public int calculateTeamsNumber() {
    log.info("Calculating teams number{}", teamService.calculateTeamsNumber());
    return teamService.calculateTeamsNumber();
  }

  @PutMapping("/sellerTeam/{sellerTeamId}/player/{playerId}/buyerTeam/{buyerTeamId}")
  public ResponseEntity<String> transfer(
      @PathVariable Integer sellerTeamId,
      @PathVariable Integer playerId,
      @PathVariable Integer buyerTeamId) {
    return ResponseEntity.ok(teamService.transferPlayer(sellerTeamId, buyerTeamId, playerId));
  }
}
