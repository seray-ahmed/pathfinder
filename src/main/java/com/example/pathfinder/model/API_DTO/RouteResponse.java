package com.example.pathfinder.model.API_DTO;

import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.enums.Level;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonSerialize
public class RouteResponse {
    private long id;
    private String description;
    private Level level;
    private String name;
    private User author;
    private List<Category> categories;
    private List<Picture> pictures;
    private List<Comment> comments;

    public RouteResponse(long id, String description, Level level, String name, User author, List<Category> categories, List<Picture> pictures, List<Comment> comments) {
        this.id = id;
        this.description = description;
        this.level = level;
        this.name = name;
        this.author = author;
        this.categories = categories;
        this.pictures = pictures;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
