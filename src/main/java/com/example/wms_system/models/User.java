package com.example.wms_system.models;

import com.example.wms_system.dto.UserDto;
import com.example.wms_system.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "password", nullable = false,columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "email", unique = true, nullable = false,columnDefinition = "VARCHAR(100)")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

   /* @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();*/

    public User(String username,
                String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username,
                String password,
                String email,
                Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User updateFields(UserDto userDto){
        if(userDto.getEmail()!=null){
            email = userDto.getEmail();
        }
        if(userDto.getUsername()!=null){
            username = userDto.getUsername();
        }
        if(userDto.getPassword()!=null){
            password = userDto.getPassword();
        }
        return this;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
