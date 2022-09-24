package com.example.footballmanager.controller;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.persistence.model.Team;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.TeamService;
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

@WebMvcTest(value = TeamController.class)
@AutoConfigureMockMvc
public class TeamControllerTest {
  private static final int MOCK_ID = 77;
  private static final String MOCK_NAME = "Team";
  private static final int MOCK_UPDATED_ID = 88;

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private TeamService teamService;

  @Test
  void getTeamTest() throws Exception {
    TeamDTO teamDTO = TeamDTO.builder().id(MOCK_ID).build();

    when(teamService.getTeam(MOCK_ID)).thenReturn(teamDTO);

    mockMvc
        .perform(get("/teams/" + MOCK_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(teamDTO.getId()));
  }

  @Test
  void createTeamTest() throws Exception {
    TeamDTO teamDTO = TeamDTO.builder().id(MOCK_ID).name(MOCK_NAME).build();

    when(teamService.createTeam(teamDTO)).thenReturn(teamDTO);

    mockMvc
        .perform(
            post("/teams")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(teamDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(teamDTO.getId()));

    verify(teamService, times(1)).createTeam(teamDTO);
  }

  @Test
  void updateTeamTest() throws Exception {
    TeamDTO teamDTO = TeamDTO.builder().id(MOCK_ID).id(MOCK_ID).build();

    when(teamService.updateTeam(MOCK_ID, teamDTO)).thenReturn(teamDTO);

    mockMvc
        .perform(
            put("/teams/" + MOCK_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(teamDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(teamDTO.getId()));

    verify(teamService, times(1)).updateTeam(MOCK_ID, teamDTO);
  }

  @Test
  void deleteTeamsTest() throws Exception {
    doNothing().when(teamService).deleteTeam(MOCK_ID);

    mockMvc.perform(delete("/teams/" + MOCK_ID)).andDo(print()).andExpect(status().isNoContent());

    verify(teamService, times(1)).deleteTeam(MOCK_ID);
  }

  @Test
  void calculateTeamsNumberTest() throws Exception {
    when(teamService.calculateTeamsNumber()).thenReturn(0);

    mockMvc
        .perform(get("/teams/count"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
  }
}
