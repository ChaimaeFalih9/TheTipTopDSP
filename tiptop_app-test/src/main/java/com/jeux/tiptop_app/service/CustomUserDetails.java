package com.jeux.tiptop_app.service;

/**
 * @author kaoutarelmofatiche
 */
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends User {

    private final User user;

    public CustomUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    public static CustomUserDetails build(User user) {
        return new CustomUserDetails(user);
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<String> roles = user.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
