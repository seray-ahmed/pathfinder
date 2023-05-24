package com.example.pathfinder.model;

import com.example.pathfinder.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Table(name = "routes")
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(nullable = false)
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "video_URL")
    private String videoURL;

    @ManyToOne
    @NotNull
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    @OneToMany(targetEntity = Picture.class, mappedBy = "route", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Picture> pictures;

    @OneToMany(targetEntity = Comment.class, mappedBy = "route", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Comment> comments;

    public Route(){
        this.pictures = new HashSet<>();
        this.comments = new HashSet<>();
        //this.categories = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public User getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Picture> getPictures() {
        return Collections.unmodifiableSet(pictures);
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }
    public List<Comment> getCommentsSortedByData() {
        return getComments().stream().sorted(Comparator.comparing(Comment::getCreated)).toList();
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
