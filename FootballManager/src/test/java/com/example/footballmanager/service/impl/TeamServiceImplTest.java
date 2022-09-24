package com.example.footballmanager.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.exception.PlayerNotFoundException;
import com.example.footballmanager.exception.TeamNotFoundException;
import com.example.footballmanager.persistence.dao.PlayerRepository;
import com.example.footballmanager.persistence.dao.TeamRepository;
import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.persistence.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {
  private static final int MOCK_ID = 77;
  private static final String MOCK_NAME = "team";
  private static final int MOCK_UPDATED_ID = 88;
  @InjectMocks private TeamServiceImpl teamService;
  @Mock private TeamRepository teamRepository;

  @Test
  void getTeamByIdTest() {
    // given
    Team expectedTeam = Team.builder().id(MOCK_ID).build();
    when(teamRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedTeam));

    // when
    TeamDTO actualTeam = teamService.getTeam(MOCK_ID);

    // then
    assertEquals(expectedTeam.getId(), actualTeam.getId());
  }

  @Test
  void updateTeamTest() {
    // given
    Team team = Team.builder().id(MOCK_ID).name(MOCK_NAME).build();
    TeamDTO updatedTeam = TeamDTO.builder().id(MOCK_UPDATED_ID).name(MOCK_NAME).build();
    when(teamRepository.findById(MOCK_ID)).thenReturn(Optional.of(team));
    when(teamRepository.save(any())).thenReturn(team);

    // when
    updatedTeam = teamService.updateTeam(MOCK_ID, updatedTeam);

    // then
    assertThat(
        updatedTeam,
        allOf(
            hasProperty("id", equalTo(team.getId())),
            hasProperty("name", equalTo(team.getName()))));
  }

  @Test
  void createTeamTest() {
    // given
    Team team = Team.builder().id(MOCK_ID).build();
    TeamDTO teamDTO = TeamDTO.builder().id(MOCK_ID).build();
    when(teamRepository.save(any())).thenReturn(team);

    // when
    TeamDTO createdTeam = teamService.createTeam(teamDTO);

    // then
    assertThat(createdTeam, hasProperty("id", equalTo(team.getId())));
  }

  @Test
  void deleteTeamTest() {
    // given
    Team team = Team.builder().id(MOCK_ID).build();
    when(teamRepository.findById(MOCK_ID)).thenReturn(Optional.of(team));
    doNothing().when(teamRepository).delete(team);

    // when
    teamService.deleteTeam(MOCK_ID);

    // then
    verify(teamRepository, times(1)).delete(team);
  }

  @Test
  void deleteTeamWithExceptionTest() {
    doThrow(TeamNotFoundException.class).when(teamRepository).findById(MOCK_ID);

    assertThrows(TeamNotFoundException.class, () -> teamService.deleteTeam(MOCK_ID));
  }

  @Test
  void calculateUsersNumberTest() {
    // given
    when(teamRepository.calculateTeamsNumber()).thenReturn(1);

    // when
    int countTeams = teamService.calculateTeamsNumber();

    // then
    assertEquals(1, countTeams);
  }
}
