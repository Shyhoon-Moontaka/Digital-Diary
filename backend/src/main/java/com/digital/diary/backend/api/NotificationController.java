package com.digital.diary.backend.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.responses.notification.NotificationResponse;
import com.digital.diary.backend.services.NotificationService;

@RestController
@RequestMapping("/api/user/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return new ResponseEntity<>(notificationService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        notificationService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
