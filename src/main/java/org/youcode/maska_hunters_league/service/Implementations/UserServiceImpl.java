package org.youcode.maska_hunters_league.service.Implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.exception.user.InvalidCredentialsException;
import org.youcode.maska_hunters_league.web.exception.user.UserDoesntExistException;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Boolean deleteUser(UUID id) {
        if (id == null) {
            throw new InvalidCredentialsException("Invalid user id");
        }
        userRepository.findById(id)
                .orElseThrow(UserDoesntExistException::new);

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> findByUsernameOrEmail(String searchKey){
        return userRepository.findByUsernameContainingOrEmailContaining(searchKey, searchKey);
    }
}
