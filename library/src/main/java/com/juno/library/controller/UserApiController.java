package com.juno.library.controller;


import com.juno.library.controller.userdto.AddUserDto;
import com.juno.library.controller.userdto.PaginatedResult;
import com.juno.library.controller.userdto.UpdateUserDto;
import com.juno.library.controller.userdto.UsersResponseDto;
import com.juno.library.domain.user.UserEntity;
import com.juno.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<UserEntity> saveUser(@RequestBody AddUserDto dto){
        UserEntity entity = userService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UsersResponseDto>> findUsers(){
        List<UsersResponseDto> users = userService.findAll().stream()
                .map(UsersResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> findUser(@PathVariable Long id){
        UserEntity entity = userService.findById(id);

        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/api/users/")
    public ResponseEntity<List<UserEntity>> findUsersByName(@RequestParam String name){
        List<UserEntity> entities = userService.findByName(name);

        return ResponseEntity.ok().body(entities);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto){
        UserEntity entity = userService.updateUser(id, dto);

        return ResponseEntity.ok().body(entity);
    }
}
