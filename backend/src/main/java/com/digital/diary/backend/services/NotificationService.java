package com.digital.diary.backend.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digital.diary.backend.mappers.NotificationMapper;
import com.digital.diary.backend.models.Notification;
import com.digital.diary.backend.repositories.NotificationRepository;
import com.digital.diary.backend.responses.notification.NotificationResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserInfoFromJwt userInfoFromJwt;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper,
            UserInfoFromJwt userInfoFromJwt) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.userInfoFromJwt = userInfoFromJwt;
    }

    public void add(String message, String redirectUrl, int userId) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRedirectUrl(redirectUrl);
        notification.setUserId(userId);
        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getAll() {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        List<NotificationResponse> notifications = notificationMapper
                .modelToResponses(notificationRepository.findAllByUserId(userId));

        Collections.reverse(notifications); // reverse in place
        return notifications;
    }

    public void delete(int id) {
        notificationRepository.deleteById(id);
    }

}
