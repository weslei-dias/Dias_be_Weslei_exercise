package com.ecore.roles.service;

import com.ecore.roles.model.Role;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

/**
 * The name of the methods was not following the naming convention.
 * Methods should always start with lower case. See https://www.geeksforgeeks.org/java-naming-conventions/
 */
public interface RolesService {

    Role createRole(Role role);

    Role getRole(UUID id);

    List<Role> getRoles();

    Role getRoleByUserIdAndTeamId(UUID roleId, UUID teamId);

}
