package org.evitorsilva.services;

import org.evitorsilva.util.DTO.requests.CreateUserRequest;
import org.evitorsilva.util.Enums.ERoles;
import org.evitorsilva.entities.AuthorityEntity;
import org.evitorsilva.entities.UserEntity;
import org.evitorsilva.repositories.UserRepository;
import org.evitorsilva.repositories.AuthorityRepository;
import org.evitorsilva.util.Interfaces.IUserDetails;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findUser(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public void createUser(CreateUserRequest createUserRequest) {
        String passwordHash = passwordEncoder.encode(createUserRequest.password());

        AuthorityEntity roleUser = authorityRepository.findByAuthority(ERoles.ROLE_USER);

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordHash);
        userEntity.setName(createUserRequest.name());
        userEntity.setEmail(createUserRequest.email());
        userEntity.getRoles().add(roleUser);

        userRepository.save(userEntity);
    }

    @Override
    public @NullMarked IUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.findUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
