package com.digital.diary.backend.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    @ManyToOne
    Users user;
    @CreationTimestamp
    LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<PostImages> postImage;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<Likes> likes;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<Comment> comments;
}