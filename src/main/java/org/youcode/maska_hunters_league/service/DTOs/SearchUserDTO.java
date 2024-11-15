package org.youcode.maska_hunters_league.service.DTOs;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchUserDTO {
    private String username;
    private String email;
}
