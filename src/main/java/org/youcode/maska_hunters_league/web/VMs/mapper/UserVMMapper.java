package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.web.VMs.UserVMs.UserVM;

@Mapper(componentModel = "spring")
public interface UserVMMapper {

    UserVM toUserVM(User user);
    User toUser(UserVM userVM);
}
