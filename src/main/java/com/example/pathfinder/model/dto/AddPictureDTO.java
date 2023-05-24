package com.example.pathfinder.model.dto;

public class AddPictureDTO {
    private String URL;

    public AddPictureDTO(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
