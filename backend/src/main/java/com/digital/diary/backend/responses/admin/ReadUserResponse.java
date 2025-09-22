package com.digital.diary.backend.responses.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
