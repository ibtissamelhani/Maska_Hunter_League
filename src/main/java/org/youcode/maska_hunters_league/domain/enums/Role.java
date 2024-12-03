package org.youcode.maska_hunters_league.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
@RequiredArgsConstructor

public enum Role {
    MEMBER(
            Set.of(Permission.CAN_PARTICIPATE,
                    Permission.CAN_VIEW_RANKINGS,
                    Permission.CAN_VIEW_COMPETITIONS)
    ),


    JURY(
            Set.of(Permission.CAN_PARTICIPATE,
                    Permission.CAN_VIEW_RANKINGS,
                    Permission.CAN_VIEW_COMPETITIONS,
                    Permission.CAN_SCORE)
    ),

    ADMIN(
            Set.of(Permission.CAN_PARTICIPATE,
                    Permission.CAN_VIEW_RANKINGS,
                    Permission.CAN_VIEW_COMPETITIONS,
                    Permission.CAN_SCORE,
                    Permission.CAN_MANAGE_COMPETITIONS,
                    Permission.CAN_MANAGE_USERS,
                    Permission.CAN_MANAGE_SPECIES,
                    Permission.CAN_MANAGE_SETTINGS)
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities =getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}
