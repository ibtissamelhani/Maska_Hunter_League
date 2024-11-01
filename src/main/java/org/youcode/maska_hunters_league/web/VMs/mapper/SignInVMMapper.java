package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.web.VMs.SignInVM;

@Mapper(componentModel = "spring")
public interface SignInVMMapper {
    User toUser(SignInVM signInVM);
    SignInVM toSignUpVM(User user);
}
