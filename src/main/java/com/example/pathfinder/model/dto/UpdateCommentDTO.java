package com.example.pathfinder.model.dto;

public class UpdateCommentDTO {
    private String textContent;

    public UpdateCommentDTO(String textContent) {
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
