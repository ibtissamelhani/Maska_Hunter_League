package org.youcode.maska_hunters_league.service;

import org.youcode.maska_hunters_league.domain.entities.Hunt;
import org.youcode.maska_hunters_league.service.DTOs.HuntRequestDTO;


public interface HuntService {
    Hunt createHunt(HuntRequestDTO huntRequestDTO);
}
