package com.example.wallet.services.user;

import com.example.wallet.exception.UsernameExistsException;
import com.example.wallet.models.User;
import com.example.wallet.persistence.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.example.wallet.helpers.Helpers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;


    @Test
    void loadUserByUsername_ShouldReturnUser_WhenUserExists() {
        when(userRepository.findByUsername("Thors"))
                .thenReturn(createMockUser());

        User result = userService.findByUsername("Thors");

        Assertions.assertEquals(createMockUser(), result);
    }

    @Test
    void loadUserByUsername_ShouldThrow_WhenUserDoesNotExist() {
        when(userRepository.findByUsername("Thors"))
                .thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("Thors"));
    }

    @Test
    void findByUsername_ShouldReturnUser_WhenUserExists() {
        when(userRepository.findByUsername("Thors"))
                .thenReturn(createMockUser());

        UserDetails result = userService.loadUserByUsername("Thors");

        Assertions.assertEquals(createMockUser().getUsername(), result.getUsername());
        Assertions.assertEquals(createMockUser().getPassword(), result.getPassword());
    }

    @Test
    void findByUsername_ShouldThrow_WhenUserDoesNotExist() {
        when(userRepository.findByUsername("Thors"))
                .thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.findByUsername("Thors"));


    }

    @Test
    void createUser_ShouldCreateUser() {
        User userDetails = createMockUser();
        userDetails.setId(0);

        userService.createUser(userDetails);

        Mockito.verify(userRepository, times(1)).save(userDetails);

    }

    @Test
    void createUser_ShouldThrow_WhenUserAlreadyExists() {
        User userDetails = createMockUser();
        userDetails.setId(0);

        Mockito.when(userRepository.findByUsername("Thors")).thenReturn(createMockUser());

        Assertions.assertThrows(UsernameExistsException.class, () -> userService.createUser(userDetails));

    }
}