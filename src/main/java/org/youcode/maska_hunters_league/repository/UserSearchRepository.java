package org.youcode.maska_hunters_league.repository;

import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.DTOs.SearchUserDTO;

import java.util.List;

public interface UserSearchRepository {
    List<User> findByCriteria(SearchUserDTO searchUserDTO);

}
