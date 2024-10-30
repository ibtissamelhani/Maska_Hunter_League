package org.youcode.maska_hunters_league.service;

import org.springframework.data.domain.Page;
import org.youcode.maska_hunters_league.domain.entities.User;

public interface UserService {
    Page<User> getAllUsersPaginated(int page, int size);
}
