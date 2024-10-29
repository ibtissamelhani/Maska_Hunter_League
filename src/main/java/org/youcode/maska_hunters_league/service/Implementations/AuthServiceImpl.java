package org.youcode.maska_hunters_league.service.Implementations;

import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.web.exception.user.EmailAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.user.InvalidUserExeption;
import org.youcode.maska_hunters_league.web.exception.user.UserNameAlreadyExistException;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
            if(user == null) {
                throw new InvalidUserExeption("user is null");
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new UserNameAlreadyExistException();
            }

            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new EmailAlreadyExistException();
            }

            return userRepository.save(user);
        }
}
