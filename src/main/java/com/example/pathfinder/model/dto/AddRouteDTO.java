package com.example.pathfinder.model.dto;

import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.enums.Level;

import java.util.Set;

public class AddRouteDTO {

    private String description;
    private String gpxCoordinates;
    private String level;
    private String name;
    private String videoURL;
    private String[] categories;

    public AddRouteDTO(String description, String gpxCoordinates, String level, String name, String videoURL, String[] categories) {
        this.description = description;
        this.gpxCoordinates = gpxCoordinates;
        this.level = level;
        this.name = name;
        this.videoURL = videoURL;
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
