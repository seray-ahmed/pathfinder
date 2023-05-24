package com.example.pathfinder.service;

import com.example.pathfinder.model.Role;
import com.example.pathfinder.model.User;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.user.CurrentUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public class CurrentUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CurrentUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with username" + username + " not found!"));

    }

    private UserDetails map(User user) {
        return new CurrentUserDetails(
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                user.getFullName(),
                user.getActive(),
                user.
                        getRoles().
                        stream().
                        map(this::map).
                        toList()
        );
    }

    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority("ROLE_" +
                role.getName().name());
    }
}
