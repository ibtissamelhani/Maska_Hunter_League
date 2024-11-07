package org.youcode.maska_hunters_league.web.VMs;


import lombok.*;
import org.youcode.maska_hunters_league.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVM {

    private UUID id;

    private String username;

    private Role role;

    private String firstName;

    private String lastName;

    private String cin;

    private String email;

    private String nationality;

    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

}
