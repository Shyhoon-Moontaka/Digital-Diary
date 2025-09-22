package com.digital.diary.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    void deleteById(int id);
    List<Comment> findAllByUser_Id(int userId);
    List<Comment> findAllByPost_Id(int postId);
}
