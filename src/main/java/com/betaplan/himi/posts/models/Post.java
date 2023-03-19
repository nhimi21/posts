package com.betaplan.himi.posts.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3,max=150)
    private String headline;
    @NotBlank
    @Size(min = 5, max = 255)
    private String description;
    private Integer nrOfLikes;
    @Column(updatable=false)
    private Date createdAt;
    @Column(updatable=false)
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userLikes;

    @ManyToOne
    private User userID;

    public Post() {
    }

    public Post(
            Long id,
            String headline,
            String description,
            Integer nrOfLikes,
            List<User> userLikes,
            User userID) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.nrOfLikes = nrOfLikes;
        this.userLikes = userLikes;
        this.userID = userID;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNrOfLikes() {
        return nrOfLikes;
    }

    public void setNrOfLikes(Integer nrOfLikes) {
        this.nrOfLikes = nrOfLikes;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
