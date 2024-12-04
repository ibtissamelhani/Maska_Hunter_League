package org.youcode.maska_hunters_league.service;

import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.AuthenticationRequest;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.AuthenticationResponse;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
