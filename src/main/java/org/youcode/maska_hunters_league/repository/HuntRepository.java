package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.maska_hunters_league.domain.entities.Hunt;

import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {

}
