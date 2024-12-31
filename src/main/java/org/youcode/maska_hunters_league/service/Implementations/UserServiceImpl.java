package org.youcode.maska_hunters_league.service.Implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.repository.UserSearchRepository;
import org.youcode.maska_hunters_league.service.DTOs.SearchUserDTO;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.exception.InvalidCredentialsException;
import org.youcode.maska_hunters_league.web.exception.user.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserSearchRepository userSearchRepository;


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
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
        return true;
    }

    @Override
    public List<User> searchUsers(SearchUserDTO searchUserDTO){
        return userSearchRepository.findByCriteria(searchUserDTO);
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

    @Override
    public User findById(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("user id cant be null");
        }
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
