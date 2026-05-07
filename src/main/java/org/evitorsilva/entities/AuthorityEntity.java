package org.evitorsilva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.evitorsilva.util.Enums.ERoles;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class AuthorityEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERoles authority;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    @Override
    public @Nullable String getAuthority() {
        return authority.name();
    }

    public void setAuthority(ERoles name) {
        this.authority = ERoles.valueOf(name.name());
    }

}
