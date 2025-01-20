package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.DTOs.SearchUserDTO;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.VMs.UserVMs.UpdateUserVM;
import org.youcode.maska_hunters_league.web.VMs.UserVMs.UserVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.UpdateUserVMMapper;
import org.youcode.maska_hunters_league.web.VMs.mapper.UserVMMapper;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserVMMapper userVMMapper;
    private final UpdateUserVMMapper updateUserVMMapper;


    @GetMapping
    public ResponseEntity<Page<UserVM>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<User> users = userService.getAllUsersPaginated(page, size);
        List<UserVM> userVMS = users.stream().map(userVMMapper::toUserVM).toList();
        Page<UserVM> userVMPage = new PageImpl<>(userVMS, users.getPageable(), users.getTotalElements());
        return ResponseEntity.ok(userVMPage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMember(@PathVariable UUID id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "deleted successfully"));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "failed to delete user"));
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserVM>> searchUsers(@RequestBody SearchUserDTO searchUserDTO) {
        List<User> result = userService.searchUsers(searchUserDTO);
        List<UserVM> userVMS = result.stream().map(userVMMapper::toUserVM).toList();
        return ResponseEntity.ok(userVMS);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserVM> updateUser(@PathVariable UUID userId,@RequestBody @Valid UpdateUserVM updateData) {
        User user = updateUserVMMapper.toUser(updateData);
        User updatedUser = userService.updateUser(userId,user);
        UserVM userVM = userVMMapper.toUserVM(updatedUser);
        return ResponseEntity.ok(userVM);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserVM> getUserById(@PathVariable UUID id) {
        User user = userService.findById(id);
        UserVM userVM = userVMMapper.toUserVM(user);
        return ResponseEntity.ok(userVM);
    }
}

