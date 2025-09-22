package com.digital.diary.backend.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostImageResponse {
    private int id;
    private byte[] data;
}