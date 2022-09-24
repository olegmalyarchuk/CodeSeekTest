package com.example.footballmanager.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.exception.PlayerNotFoundException;
import com.example.footballmanager.persistence.dao.PlayerRepository;
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
public class PlayerServiceImplTest {
  private static final int MOCK_ID = 77;
  private static final String MOCK_NAME = "name";
  private static final String MOCK_SURNAME = "surname";
  private static final int MOCK_UPDATED_ID = 88;
  @InjectMocks private PlayerServiceImpl playerService;
  @Mock private PlayerRepository playerRepository;

  @Test
  void getPlayerByIdTest() {
    // given
    Player expectedPlayer = Player.builder().id(MOCK_ID).build();
    when(playerRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedPlayer));

    // when
    PlayerDTO actualPlayer = playerService.getPlayer(MOCK_ID);

    // then
    assertEquals(expectedPlayer.getId(), actualPlayer.getId());
  }

  @Test
  void getPlayersByTeamIdTest() {
    // given
    Team team = Team.builder().id(3).build();
    Player player = Player.builder().id(MOCK_ID).team(team).build();
    List<Player> players = new ArrayList<>();
    players.add(player);
    when(playerRepository.findAllByTeamId(3)).thenReturn(players);

    // when
    List<PlayerDTO> actualPlayers = playerService.getPlayersByTeamId(3);

    // then
    assertEquals(actualPlayers.size(), players.size());
  }

  @Test
  void updatePlayerTest() {
    // given
    Player player = Player.builder().id(MOCK_ID).name(MOCK_NAME).build();
    PlayerDTO updatedPlayer = PlayerDTO.builder().id(MOCK_UPDATED_ID).name(MOCK_NAME).build();
    when(playerRepository.findById(MOCK_ID)).thenReturn(Optional.of(player));
    when(playerRepository.save(any())).thenReturn(player);

    // when
    updatedPlayer = playerService.updatePlayer(MOCK_ID, updatedPlayer);

    // then
    assertThat(
        updatedPlayer,
        allOf(
            hasProperty("id", equalTo(player.getId())),
            hasProperty("name", equalTo(player.getName()))));
  }

  @Test
  void createPlayerTest() {
    // given
    Player player = Player.builder().id(MOCK_ID).build();
    PlayerDTO playerDTO = PlayerDTO.builder().id(MOCK_ID).build();
    when(playerRepository.save(any())).thenReturn(player);

    // when
    PlayerDTO createdPlayer = playerService.createPlayer(playerDTO);

    // then
    assertThat(createdPlayer, hasProperty("id", equalTo(player.getId())));
  }

  @Test
  void deletePlayerTest() {
    // given
    Player player = Player.builder().id(MOCK_ID).build();
    when(playerRepository.findById(MOCK_ID)).thenReturn(Optional.of(player));
    doNothing().when(playerRepository).delete(player);

    // when
    playerService.deletePlayer(MOCK_ID);

    // then
    verify(playerRepository, times(1)).delete(player);
  }

  @Test
  void deletePlayerWithExceptionTest() {
    doThrow(PlayerNotFoundException.class).when(playerRepository).findById(MOCK_ID);

    assertThrows(PlayerNotFoundException.class, () -> playerService.deletePlayer(MOCK_ID));
  }

  @Test
  void calculateUsersNumberTest() {
    // given
    when(playerRepository.calculatePlayersNumber()).thenReturn(1);

    // when
    int countPlayers = playerService.calculatePlayersNumber();

    // then
    assertEquals(1, countPlayers);
  }
}
