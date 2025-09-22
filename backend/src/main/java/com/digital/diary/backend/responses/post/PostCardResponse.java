package com.digital.diary.backend.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCardResponse {
    private int id;
    private String title;
    private String userFirstName;
    private String userLastName;
    private String createdAt;
}
