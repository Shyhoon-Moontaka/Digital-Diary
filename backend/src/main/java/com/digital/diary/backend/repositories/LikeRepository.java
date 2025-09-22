package com.digital.diary.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Likes;

public interface LikeRepository extends JpaRepository<Likes, Integer> {
    void deleteLikeById(int id);

    List<Likes> findAllByPost_Id(int postId);

    List<Likes> findAllByUser_Id(int userId);

    Optional<Likes> findByUser_IdAndPost_Id(int userId, int postId);
}
