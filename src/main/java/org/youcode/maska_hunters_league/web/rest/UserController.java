package org.youcode.maska_hunters_league.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.VMs.UserVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.UserVMMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/api/users")
public class UserController {

    private final UserService userService;
    private final UserVMMapper userVMMapper;

    public UserController(UserService userService,
                          UserVMMapper userVMMapper) {
        this.userService = userService;
        this.userVMMapper = userVMMapper;
    }

    @GetMapping
    public ResponseEntity<Page<UserVM>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<User> users = userService.getAllUsersPaginated(page, size);
        List<UserVM> userVMS = users.stream().map(userVMMapper::toUserVM).toList();
        Page<UserVM> userVMPage = new PageImpl<>(userVMS, users.getPageable(), users.getTotalElements());
        return ResponseEntity.ok(userVMPage);
    }
}
