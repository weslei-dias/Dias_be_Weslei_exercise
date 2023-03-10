package com.ecore.roles.service.impl;

import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.MembershipsService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.ofNullable;

/**
 * The method assignRoleToMembership was refactored to have the 2 other methods to check if Role and Membership exists.
 *
 * The @Autowired annotation is not necessary in the constructor since there is only one constructor.
 * See more at https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/htmlsingle/#beans-autowired-annotation
 */
@Log4j2
@Service
public class MembershipsServiceImpl implements MembershipsService {

    private final MembershipRepository membershipRepository;
    private final RoleRepository roleRepository;

    public MembershipsServiceImpl(
            MembershipRepository membershipRepository,
            RoleRepository roleRepository) {
        this.membershipRepository = membershipRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Membership assignRoleToMembership(@NonNull Membership membership) {
        checkIfRoleExists(membership);
        checkIfMembershipExists(membership);
        return membershipRepository.save(membership);
    }

    private void checkIfRoleExists(Membership membership) {
        UUID roleId = ofNullable(membership.getRole()).map(Role::getId)
                .orElseThrow(() -> new InvalidArgumentException(Role.class));
        roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));
    }

    private void checkIfMembershipExists(Membership m) {
        if (membershipRepository.findByUserIdAndTeamId(m.getUserId(), m.getTeamId())
                .isPresent()) {
            throw new ResourceExistsException(Membership.class);
        }
    }

    @Override
    public List<Membership> getMemberships(@NonNull UUID rid) {
        return membershipRepository.findByRoleId(rid);
    }

    @Override
    public Optional<Membership> getMembershipByUserIdAndTeamId(UUID userId, UUID teamId) {
        return membershipRepository.findByUserIdAndTeamId(userId, teamId);
    }
}
