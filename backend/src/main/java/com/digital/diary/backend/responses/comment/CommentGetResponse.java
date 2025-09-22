package com.digital.diary.backend.responses.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetResponse {
    private int id;
    private int userId;
    private String description;
    private String userFirstName;
    private String userLastName;
}