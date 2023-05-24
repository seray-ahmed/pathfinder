package com.example.pathfinder.model.dto;

import javax.validation.constraints.*;


public class UserRegistrationDTO {

    @Size(min = 4, max = 20, message = "Username length must be more than 4 and less than 20 characters!")
    @NotBlank(message = "Username should be provided!")
    private String username;

    @Size(min = 5, max = 20)
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @Min(0)
    @Max(90)
    private int age;

    @Size(min = 5, max = 20)
    @NotBlank
    private String password;
    @Size(min = 5, max = 20)
    @NotBlank
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
