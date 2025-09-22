package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.digital.diary.backend.models.Notification;
import com.digital.diary.backend.responses.notification.NotificationResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "message", target = "message")
    @Mapping(source = "redirectUrl", target = "redirectUrl")
    NotificationResponse modelToResponse(Notification notification);

    List<NotificationResponse> modelToResponses(List<Notification> notifications);
}
