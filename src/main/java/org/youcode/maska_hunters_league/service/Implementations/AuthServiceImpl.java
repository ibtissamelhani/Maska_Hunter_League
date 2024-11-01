package org.youcode.maska_hunters_league.service.Implementations;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.web.exception.user.EmailAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.InvalidCredentialsException;
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

            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new UserNameAlreadyExistException();
            }

            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new EmailAlreadyExistException();
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            return userRepository.save(user);
        }

        @Override
    public Boolean login(User user) {
            if (user == null) {
                throw new InvalidUserExeption("user is null");
            }
            User existingUser = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new InvalidCredentialsException("username or password is incorrect"));

            if (BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
                return true;
            } else {
                throw new InvalidCredentialsException("username or password is incorrect");
            }
    }

}
