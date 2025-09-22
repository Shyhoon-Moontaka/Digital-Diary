package com.digital.diary.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findAllByUser_Id(int userId);

    Optional<Follow> findByUser_IdAndFollowing_Id(int userId, int followingId);

    List<Follow> findByFollowing_Id(int followingId);

    List<Follow> findByUser_Id(int userId);
}
