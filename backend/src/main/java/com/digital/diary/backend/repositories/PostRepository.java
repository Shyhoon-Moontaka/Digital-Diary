package com.digital.diary.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUser_IdOrderByIdDesc(int userId);
    void deleteById(int id);
}
