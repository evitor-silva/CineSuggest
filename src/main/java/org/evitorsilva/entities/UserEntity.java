package org.evitorsilva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.evitorsilva.util.Enums.ERoles;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
