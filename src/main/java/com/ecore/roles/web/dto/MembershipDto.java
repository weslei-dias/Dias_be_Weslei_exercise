package com.ecore.roles.web.dto;

import com.ecore.roles.model.Membership;
import com.ecore.roles.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MembershipDto {

    @JsonProperty
    private UUID id;

    @JsonProperty
    @Valid
    @NotNull
    @EqualsAndHashCode.Include
    private UUID roleId;

    /**
     * The name of the attribute and the name of the property can be different,
     * as long as the @JsonProperty annotation is used to specify the JSON property name.
     * However in this case there is no need to have a different name
     */
    @JsonProperty(value = "userId")
    @Valid
    @NotNull
    @EqualsAndHashCode.Include
    private UUID userId;

    @JsonProperty
    @Valid
    @NotNull
    @EqualsAndHashCode.Include
    private UUID teamId;

    public static MembershipDto fromModel(Membership membership) {
        if (membership == null) {
            return null;
        }
        return MembershipDto.builder()
                .id(membership.getId())
                .roleId(ofNullable(membership.getRole()).map(Role::getId).orElse(null))
                .userId(membership.getUserId())
                .teamId(membership.getTeamId())
                .build();
    }

    public Membership toModel() {
        return Membership.builder()
                .id(this.id)
                .role(Role.builder().id(this.roleId).build())
                .userId(this.userId)
                .teamId(this.teamId)
                .build();
    }

}
