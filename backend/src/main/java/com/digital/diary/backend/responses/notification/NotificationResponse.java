package com.digital.diary.backend.responses.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private int id;
    private String message;
    private String redirectUrl;
}
