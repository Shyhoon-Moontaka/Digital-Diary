package com.digital.diary.backend.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private int id;
    private int authorId;
    private String title;
    private String description;
    private String userFirstName;
    private String userLastName;
    private String createdAt;
}