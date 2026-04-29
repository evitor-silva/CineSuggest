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

    public Set<AuthorityEntity> getRoles() {
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(ERoles eRoles) {
        AuthorityEntity authority = new AuthorityEntity();
        authority.setAuthority(eRoles);
        this.roles.add(authority);
    }
}
