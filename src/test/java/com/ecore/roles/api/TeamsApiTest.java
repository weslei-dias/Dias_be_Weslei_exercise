package com.ecore.roles.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecore.roles.web.TeamsApi;
import com.ecore.roles.web.dto.TeamDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TeamsApiTest {

    private TeamsApi teamsApi;

    @BeforeEach
    public void setUp() {
        teamsApi = mock(TeamsApi.class);
    }

    @Test
    public void testGetTeams() {
        List<TeamDto> expectedTeams = new ArrayList<>();
        expectedTeams.add(new TeamDto(UUID.randomUUID(), "Team A", UUID.randomUUID(), List.of(UUID.randomUUID())));
        expectedTeams.add(new TeamDto(UUID.randomUUID(), "Team B", UUID.randomUUID(), List.of(UUID.randomUUID())));

        when(teamsApi.getTeams()).thenReturn(new ResponseEntity<>(expectedTeams, HttpStatus.OK));

        ResponseEntity<List<TeamDto>> responseEntity = teamsApi.getTeams();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedTeams, responseEntity.getBody());
    }

    @Test
    public void testGetTeam() {
        UUID teamId = UUID.randomUUID();
        TeamDto expectedTeam = new TeamDto(teamId, "Team A", UUID.randomUUID(), List.of(UUID.randomUUID()));

        when(teamsApi.getTeam(teamId)).thenReturn(new ResponseEntity<>(expectedTeam, HttpStatus.OK));

        ResponseEntity<TeamDto> responseEntity = teamsApi.getTeam(teamId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedTeam, responseEntity.getBody());
    }

}
