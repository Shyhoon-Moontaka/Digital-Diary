package com.digital.diary.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.PostImages;

public interface PostImageRepository extends JpaRepository<PostImages, Integer> {
    PostImages findByPostId(int postId);
}
