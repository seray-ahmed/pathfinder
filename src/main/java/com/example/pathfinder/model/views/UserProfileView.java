package com.example.pathfinder.model.views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserProfileView {
    private final Long id;

    private final String fullName;
    private final String username;

    private Boolean isActive;
    private final String email;

    private final LocalDateTime registrationDate;

    private final Integer age;
    private final String description;

    private final String level;


    public UserProfileView(Long id, String fullName, String username, boolean isActive, String email, LocalDateTime registrationDate, Integer age, String description, String level) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.age = age;
        this.description = description;
        this.isActive = isActive;
        this.level = level;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public String getRegistrationDateFormatted() {
        return dateFormatter(this.getRegistrationDate());
    }

    public Integer getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getLevel() {
        return level;
    }

    private String dateFormatter(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formatDateTime = dateTime.format(formatter);
        return formatDateTime;
    }
}
