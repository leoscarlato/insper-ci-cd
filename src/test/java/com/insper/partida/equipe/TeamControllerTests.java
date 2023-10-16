package com.insper.partida.equipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insper.partida.equipe.dto.SaveTeamDTO;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    TeamController teamController;

    @Mock
    TeamService teamService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(teamController)
                .build();
    }

    @Test
    void test_listTeams() throws Exception {

        TeamReturnDTO team = new TeamReturnDTO();
        team.setIdentifier("time-1");

        List<TeamReturnDTO> times = new ArrayList<>();
        times.add(team);

        Mockito.when(teamService.listTeams()).thenReturn(times);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/team"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(times), resp);

    }

    @Test
    void test_saveTeam() throws Exception {

        SaveTeamDTO saveTeamDto = new SaveTeamDTO();

        TeamReturnDTO createdTeam = new TeamReturnDTO();

        Mockito.when(teamService.saveTeam(Mockito.any(SaveTeamDTO.class))).thenReturn(createdTeam);

        ObjectMapper om = new ObjectMapper();

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/team")
                        .contentType("application/json")
                        .content(om.writeValueAsString(saveTeamDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(createdTeam), resp);

    }

    @Test
    void test_deleteTeam() throws Exception {

        String teamIdentifier = "test-identifier";
        
        Mockito.doNothing().when(teamService).deleteTeam(teamIdentifier);

        mockMvc.perform(MockMvcRequestBuilders.delete("/team/" + teamIdentifier))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }






}
