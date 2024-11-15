package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.web.VMs.UserVMs.UpdateUserVM;

@Mapper(componentModel = "spring")
public interface UpdateUserVMMapper {
    User toUser(UpdateUserVM updateUserVM);
    UpdateUserVM toUpdateUserVM(User user);
}
