package com.example.wallet.services.user;

import com.example.wallet.exception.UsernameExistsException;
import com.example.wallet.models.User;
import com.example.wallet.persistence.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Used by Spring Security to fetch user details when logging in
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
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
    public void createUser(UserDetails userDetails) {
        if (userExists(userDetails.getUsername())) {
            throw new UsernameExistsException("User with username: " + userDetails.getUsername() + " already exists");
        } else {
            User user = fromUserDetails(userDetails);
            userRepository.save(user);
        }
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

    private User fromUserDetails(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        return user;
    }
}