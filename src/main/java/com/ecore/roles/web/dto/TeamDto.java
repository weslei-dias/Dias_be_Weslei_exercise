package com.ecore.roles.web.dto;

import com.ecore.roles.client.model.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Personally I don't like to have the JsonInclude.Include.NON_NULL, because if those fields are null, they won't
 * be present on the response. This can generate a breaking change for clients that are consuming the api and are expecting to
 * have this field for example.
 * If the client knows what is the correct behaviour of those fields. Please ignore this change.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TeamDto {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    private UUID teamLeadId;

    @JsonProperty
    private List<UUID> teamMemberIds;

    public static TeamDto fromModel(Team team) {
        if (team == null) {
            return null;
        }
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .teamLeadId(team.getTeamLeadId())
                .teamMemberIds(team.getTeamMemberIds())
                .build();
    }
}
