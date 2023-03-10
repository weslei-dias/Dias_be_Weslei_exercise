package com.ecore.roles.web.rest;

import com.ecore.roles.model.Role;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.RolesApi;
import com.ecore.roles.web.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecore.roles.web.dto.RoleDto.fromModel;

/**
 * The getRoles() and getRole() method in the class uses a POST request instead of a GET request, which violates
 * the RESTful API design principles.
 *
 * Created new static method inside RolesDto just to have this coneversion into a single place.
 *
 * On the method createRole() the http code should be 201(CREATED) according to the design principles.
 *
 * The new method to get the roles based on the userId and teamId was created using
 * to receive userID and teamId as a filter.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RolesRestController implements RolesApi {

    private final RolesService rolesService;

    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<RoleDto> createRole(
            @Valid @RequestBody RoleDto role) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fromModel(rolesService.createRole(role.toModel())));
    }

    @Override
    @GetMapping (
            produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getRoles() {

        List<Role> roles = rolesService.getRoles();

        List<RoleDto> roleDtoList = RoleDto.getRoleDtoList(roles);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleDtoList);
    }

    @Override
    @GetMapping(path = "/search", produces = {"application/json"})
    public ResponseEntity<RoleDto> getRoleByUserAndTeamId(@RequestParam UUID userId, @RequestParam UUID teamId) {

        var role = rolesService.getRoleByUserIdAndTeamId(userId, teamId);
        return ResponseEntity.ok(fromModel(role));
    }

    @Override
    @GetMapping(
            path = "/{roleId}",
            produces = {"application/json"})
    public ResponseEntity<RoleDto> getRole(
            @PathVariable UUID roleId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fromModel(rolesService.getRole(roleId)));
    }

}
