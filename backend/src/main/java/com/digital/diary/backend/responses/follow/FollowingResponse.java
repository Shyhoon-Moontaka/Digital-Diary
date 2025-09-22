package com.digital.diary.backend.responses.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowingResponse {
    private int id;
    private String firstName;
    private String lastName;
}
