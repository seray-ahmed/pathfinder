package com.example.pathfinder.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CurrentUserDetails implements UserDetails {

    private final Long Id;

    private final String password;
    private String username;
    private final String fullName;

    private boolean isActive;
    private final Collection<GrantedAuthority> authorities;

    public CurrentUserDetails(Long Id, String password, String username, String fullName, boolean isActive, Collection<GrantedAuthority> authorities) {
        this.Id = Id;
        this.password = password;
        this.username = username;
        this.fullName = fullName;
        this.isActive = isActive;
        this.authorities = authorities;
    }
    public Long getId() {
        return Id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return fullName.split(" ")[0];
    }

    public String getLastName() {
        return fullName.split(" ")[1];
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
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
