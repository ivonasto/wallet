package com.example.wallet.services.user;

import com.example.wallet.models.User;
import com.example.wallet.persistence.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsManager,UserService {

    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return CustomUserDetails.build(user);
    }
    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return user;
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException("Out of scope for task");
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("Out of scope for task");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Out of scope for task");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Out of scope for task");
    }

    @Override
    public boolean userExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}