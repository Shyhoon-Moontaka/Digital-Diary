package com.digital.diary.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findAllByCommentId(int commentId);
}
