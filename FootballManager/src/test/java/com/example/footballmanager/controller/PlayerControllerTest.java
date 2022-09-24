package com.example.footballmanager.controller;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(value = PlayerController.class)
@AutoConfigureMockMvc
public class PlayerControllerTest {
  private static final int MOCK_ID = 77;
  private static final String MOCK_NAME = "Name";
  private static final int MOCK_TEAM_ID = 1;
  private static final int MOCK_UPDATED_ID = 88;

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private PlayerService playerService;

  @Test
  void getPlayerTest() throws Exception {
    PlayerDTO playerDTO = PlayerDTO.builder().id(MOCK_ID).build();

    when(playerService.getPlayer(MOCK_ID)).thenReturn(playerDTO);

    mockMvc
        .perform(get("/players/" + MOCK_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(playerDTO.getId()));
  }

  @Test
  void createPlayerTest() throws Exception {
    PlayerDTO playerDTO = PlayerDTO.builder().id(MOCK_ID).name(MOCK_NAME).build();

    when(playerService.createPlayer(playerDTO)).thenReturn(playerDTO);

    mockMvc
        .perform(
            post("/players")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(playerDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(playerDTO.getId()));

    verify(playerService, times(1)).createPlayer(playerDTO);
  }

  @Test
  void updatePlayerTest() throws Exception {
    PlayerDTO playerDTO = PlayerDTO.builder().id(MOCK_ID).id(MOCK_ID).build();

    when(playerService.updatePlayer(MOCK_ID, playerDTO)).thenReturn(playerDTO);

    mockMvc
        .perform(
            put("/players/" + MOCK_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(playerDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(playerDTO.getId()));

    verify(playerService, times(1)).updatePlayer(MOCK_ID, playerDTO);
  }

  @Test
  void deletePlayerTest() throws Exception {
    doNothing().when(playerService).deletePlayer(MOCK_ID);

    mockMvc.perform(delete("/players/" + MOCK_ID)).andDo(print()).andExpect(status().isNoContent());

    verify(playerService, times(1)).deletePlayer(MOCK_ID);
  }

  @Test
  void calculatePlayersNumberTest() throws Exception {
    when(playerService.calculatePlayersNumber()).thenReturn(0);

    mockMvc
        .perform(get("/players/count"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
  }

  @Test
  void getPlayersByTeamIdTest() throws Exception {
    PlayerDTO playerDTO = PlayerDTO.builder().id(MOCK_ID).teamId(MOCK_TEAM_ID).build();
    List<PlayerDTO> playerDTOS = new ArrayList<>();
    playerDTOS.add(playerDTO);

    when(playerService.getPlayersByTeamId(MOCK_TEAM_ID)).thenReturn(playerDTOS);

    mockMvc
        .perform(get("/players/getByTeamId/{teamId}", MOCK_TEAM_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].teamId").value(playerDTO.getTeamId()));
  }
}
