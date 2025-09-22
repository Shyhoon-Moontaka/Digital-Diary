package com.digital.diary.backend.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImageResponse {
    private int id;
    private byte[] data;
}