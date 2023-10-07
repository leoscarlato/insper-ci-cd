package com.insper.partida.equipe;

import com.insper.partida.equipe.dto.TeamReturnDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.insper.partida.equipe.dto.SaveTeamDTO;
import com.insper.partida.equipe.exception.TeamAlreadyExistsException;
import com.insper.partida.equipe.exception.TeamNotFoundException;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTests {

    @InjectMocks
    TeamService teamService;

    @Mock
    TeamRepository teamRepository;


    @Test
    void test_listTeams() {
        Mockito.when(teamRepository.findAll()).thenReturn(new ArrayList<>());

        List<TeamReturnDTO> resp = teamService.listTeams();

        Assertions.assertEquals(0, resp.size());
    }

    @Test
    void test_listTeamsNotEmpty() {

        Team team = getTeam();

        List<Team> lista = new ArrayList<>();
        lista.add(team);

        Mockito.when(teamRepository.findAll()).thenReturn(lista);

        List<TeamReturnDTO> resp = teamService.listTeams();

        Assertions.assertEquals(1, resp.size());
    }

    @Test
    void test_saveTeam() {

        SaveTeamDTO saveTeam = new SaveTeamDTO();
        saveTeam.setIdentifier("time-1");
        saveTeam.setName("Time 1");

        Mockito.when(teamRepository.existsByIdentifier(saveTeam.getIdentifier())).thenReturn(false);

        Team team = getTeam();

        Mockito.when(teamRepository.save(Mockito.any(Team.class))).thenReturn(team);

        TeamReturnDTO resp = teamService.saveTeam(saveTeam);

        Assertions.assertEquals("time-1", resp.getIdentifier());
        Assertions.assertEquals("Time 1", resp.getName());
    }

    @Test
    void test_saveTeamAlreadyExists() {

        SaveTeamDTO saveTeam = new SaveTeamDTO();
        saveTeam.setIdentifier("time-1");
        saveTeam.setName("Time 1");

        Mockito.when(teamRepository.existsByIdentifier(saveTeam.getIdentifier())).thenReturn(true);

        Assertions.assertThrows(TeamAlreadyExistsException.class, () -> {
            teamService.saveTeam(saveTeam);
        });
    }

    @Test
    void test_deleteTeam() {

        Team team = getTeam();

        Mockito.when(teamRepository.findByIdentifier(team.getIdentifier())).thenReturn(team);

        teamService.deleteTeam(team.getIdentifier());

        Mockito.verify(teamRepository, Mockito.times(1)).delete(team);
    }

    @Test
    void test_deleteTeamNotFound() {

        Team team = getTeam();

        Mockito.when(teamRepository.findByIdentifier(team.getIdentifier())).thenReturn(null);

        Assertions.assertThrows(TeamNotFoundException.class, () -> {
            teamService.deleteTeam(team.getIdentifier());
        });
    }

    @Test
    void test_getTeam() {

        Team team = getTeam();

        Mockito.when(teamRepository.findByIdentifier(team.getIdentifier())).thenReturn(team);

        Team resp = teamService.getTeam(team.getIdentifier());

        Assertions.assertEquals(team, resp);
    }

    @Test
    void test_getTeamNotFound() {

        Team team = getTeam();

        Mockito.when(teamRepository.findByIdentifier(team.getIdentifier())).thenReturn(null);

        Assertions.assertThrows(TeamNotFoundException.class, () -> {
            teamService.getTeam(team.getIdentifier());
        });
    }




    private static Team getTeam() {
        Team team = new Team();
        team.setId("1");
        team.setIdentifier("time-1");
        team.setName("Time 1");
        return team;
    }


}
