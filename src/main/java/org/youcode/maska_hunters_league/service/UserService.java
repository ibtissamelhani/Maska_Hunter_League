package org.youcode.maska_hunters_league.service;

import org.springframework.data.domain.Page;
import org.youcode.maska_hunters_league.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Page<User> getAllUsersPaginated(int page, int size);
    Boolean deleteUser(UUID id);
    List<User> findByUsernameOrEmail(String searchKey);
    User updateUser(UUID id,User user);
    User findById(UUID id);

}
