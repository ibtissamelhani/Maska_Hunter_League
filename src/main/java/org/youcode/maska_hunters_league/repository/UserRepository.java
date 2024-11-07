package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.maska_hunters_league.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByUsernameContainingOrEmailContaining(String name, String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<User> searchByUsernameOrEmail(@Param("term") String term);
}
