package com.digital.diary.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.UserImage;


public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    UserImage findByUserId(int userId);
}
