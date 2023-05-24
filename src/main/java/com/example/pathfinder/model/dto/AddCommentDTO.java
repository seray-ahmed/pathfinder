package com.example.pathfinder.model.dto;

public class AddCommentDTO {
    private String message;

    public AddCommentDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
