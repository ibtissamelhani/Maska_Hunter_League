package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.web.VMs.SignUpVM;

@Mapper(componentModel = "spring")
public interface SignUpVMMapper {
    User toUser(SignUpVM signUpVM);
    SignUpVM toSignUpVM(User user);
}
