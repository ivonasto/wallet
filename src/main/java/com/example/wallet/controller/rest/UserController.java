package com.example.wallet.controller.rest;


import com.example.wallet.dtos.CreateUserRequest;
import com.example.wallet.dtos.UserResponse;
import com.example.wallet.exception.UsernameExistsException;
import com.example.wallet.mappers.UserMapper;
import com.example.wallet.models.User;
import com.example.wallet.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    UserMapper userMapper;
    UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Operation(description = "Start here. Create a user and password, which will be used to generate a token")
    @PostMapping()
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserDetails user = userMapper.fromCreateUserRequest(createUserRequest);

        try {
            userService.createUser(user);

        } catch (UsernameExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

    }

    @GetMapping("/{username}")
    public UserResponse get(@PathVariable String username, Principal principal) {
        boolean principalIsUser = principal.getName().equals(username);

        try {
            User user = userService.findByUsername(username);
            if (principalIsUser) {
                return userMapper.fromUser(user);
            } else {
                user.setPassword("******");
                return userMapper.fromUser(user);
            }
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

    }

}
