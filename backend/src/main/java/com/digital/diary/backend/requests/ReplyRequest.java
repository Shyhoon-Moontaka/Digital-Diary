package com.digital.diary.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRequest {
    private int commentId;
    private int userId;
    private String description;
}
