package com.example.wallet.services.user;

import com.example.wallet.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;

public interface UserService extends UserDetailsService, UserDetailsManager {
    User findByUsername(String username);
}
