package com.example.pathfinder.model.dto;

public class UpdateUserDetailsDTO {
    private final String fullName;
    private final String username;
    private final String email;
    private final Integer age;

    public UpdateUserDetailsDTO(String fullName, String username, String email, Integer age) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
