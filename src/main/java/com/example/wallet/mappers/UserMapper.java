package com.example.wallet.mappers;

import com.example.wallet.dtos.CreateUserRequest;
import com.example.wallet.dtos.UserResponse;
import com.example.wallet.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromCreateUserRequest(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(createUserRequest.username());
        user.setPassword(createUserRequest.password());
        return user;
    }

    public UserResponse fromUser(User user) {
        return new UserResponse(user.getUsername(), user.getPassword());

    }
}
