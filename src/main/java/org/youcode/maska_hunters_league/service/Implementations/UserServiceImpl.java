package org.youcode.maska_hunters_league.service.Implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.exception.InvalidCredentialsException;
import org.youcode.maska_hunters_league.web.exception.user.UserNotFoundException;

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
                .orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> findByUsernameOrEmail(String searchKey){
        return userRepository.findByUsernameContainingOrEmailContaining(searchKey, searchKey);
    }

    @Override
    public User updateUser(UUID id,User user){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userToUpdate.setUsername(user.getUsername() != null ? user.getUsername() : userToUpdate.getUsername());
        userToUpdate.setFirstName(user.getFirstName() != null ? user.getFirstName() : userToUpdate.getFirstName());
        userToUpdate.setLastName(user.getLastName() != null ? user.getLastName() : userToUpdate.getLastName());
        userToUpdate.setEmail(user.getEmail() != null ? user.getEmail() : userToUpdate.getEmail());
        userToUpdate.setNationality(user.getNationality() != null ? user.getNationality() : userToUpdate.getNationality());
        userToUpdate.setLicenseExpirationDate(user.getLicenseExpirationDate() != null ? user.getLicenseExpirationDate() : userToUpdate.getLicenseExpirationDate());

        return userRepository.save(userToUpdate);
    }
}
