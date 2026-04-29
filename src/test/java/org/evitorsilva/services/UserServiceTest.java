package org.evitorsilva.services;

import org.evitorsilva.util.DTO.requests.LoginRequest;
import org.evitorsilva.entities.UserEntity;
import org.evitorsilva.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser() {
        LoginRequest request = new LoginRequest("teste@exemplo.com", "senha123");
        UserEntity user = new UserEntity();
        user.setEmail("teste@exemplo.com");

        when(userRepository.findFirstByEmail("teste@exemplo.com"))
                .thenReturn(Optional.of(user));

        Optional<UserEntity> result = userService.findUser(request.email());

        assertTrue(result.isPresent());
        assertEquals("teste@exemplo.com", result.get().getEmail());
        verify(userRepository, times(1)).findFirstByEmail("teste@exemplo.com");
    }

}