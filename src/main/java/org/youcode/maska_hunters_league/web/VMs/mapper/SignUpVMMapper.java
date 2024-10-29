package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.web.VMs.SignUpVM;

@Mapper(componentModel = "spring")
public interface SignUpVMMapper {
    SignUpVMMapper INSTANCE = Mappers.getMapper(SignUpVMMapper.class);
    User toUser(SignUpVM signUpVM);
    SignUpVM toSignUpVM(User user);
}
