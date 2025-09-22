package com.digital.diary.backend.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<UserImage> userImage;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Follow> following;
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    Set<Follow> followers;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Post> posts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Likes> likes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Comment> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Reply> replies;
}
