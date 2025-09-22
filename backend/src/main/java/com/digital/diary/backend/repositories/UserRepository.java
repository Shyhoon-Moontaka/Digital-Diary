package com.digital.diary.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.diary.backend.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    void deleteById(int id);

    Users findByEmail(String email);
}
