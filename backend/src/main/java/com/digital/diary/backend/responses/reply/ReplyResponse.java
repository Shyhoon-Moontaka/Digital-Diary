package com.digital.diary.backend.responses.reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponse {
    private int id;
    private String userFirstName;
    private String userLastName;
    private String description;
}