package org.evitorsilva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.evitorsilva.util.Enums.ERoles;
import org.evitorsilva.util.Interfaces.IUserDetails;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity implements IUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String name;

    private String email;

    private String password;

    public UserEntity() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<AuthorityEntity> roles = new HashSet<>();

    public void setRoles(ERoles eRoles) {
        AuthorityEntity authority = new AuthorityEntity();
        authority.setAuthority(eRoles);
        this.roles.add(authority);
    }

    @Override
    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public @NullMarked String getUsername() {
        return getName();
    }
}
