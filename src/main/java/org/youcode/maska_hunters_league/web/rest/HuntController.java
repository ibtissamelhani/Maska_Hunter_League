package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.maska_hunters_league.domain.entities.Hunt;
import org.youcode.maska_hunters_league.service.DTOs.HuntRequestDTO;
import org.youcode.maska_hunters_league.service.HuntService;

@RestController
@RequestMapping("v1/api/hunt")
@AllArgsConstructor
public class HuntController {

    private final HuntService huntService;


    @PostMapping
    public ResponseEntity<String> createHunt(@RequestBody @Valid HuntRequestDTO huntRequestDTO) {
        Hunt hunt = huntService.createHunt(huntRequestDTO);
        return ResponseEntity.ok("hunt created");
    }
}
