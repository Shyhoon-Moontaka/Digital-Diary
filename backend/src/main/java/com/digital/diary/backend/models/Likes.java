package com.digital.diary.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NotNull
    @ManyToOne
    Users user;
    @NotNull
    @ManyToOne
    Post post;
}
