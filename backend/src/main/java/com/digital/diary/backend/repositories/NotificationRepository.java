package com.digital.diary.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByUserId(int userId);
}
