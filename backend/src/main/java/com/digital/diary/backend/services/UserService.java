package com.digital.diary.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digital.diary.backend.mappers.UserMapper;
import com.digital.diary.backend.models.Follow;
import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.repositories.FollowRepository;
import com.digital.diary.backend.repositories.UserRepository;
import com.digital.diary.backend.responses.user.UserResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final UserInfoFromJwt userInfoFromJwt;

    public UserService(UserInfoFromJwt userInfoFromJwt, UserMapper userMapper, UserRepository userRepository,
            FollowRepository followRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.userInfoFromJwt = userInfoFromJwt;
    }

    public List<UserResponse> getAll() {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        List<Users> allUsersExceptMe = userRepository.findAll()
                .stream()
                .filter(user -> user.getId() != userId)
                .collect(Collectors.toList());
        return userMapper.usersToResponses(allUsersExceptMe);
    }

    public UserResponse getOtherProfileData(int id) {
        Users user = userRepository.findById(id).orElse(null);
        return userMapper.userToResponse(user);
    }

    public UserResponse getMyProfileData() {
        Users user = userRepository.findById(userInfoFromJwt.getMineFromToken().getId()).orElse(null);
        return userMapper.userToResponse(user);
    }

    public boolean isFollowing(int followingId) {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        Optional<Follow> follow = followRepository.findByUser_IdAndFollowing_Id(userId, followingId);
        return follow.isPresent();
    }

    public void delete() {
        userRepository.deleteById(userInfoFromJwt.getMineFromToken().getId());
    }
}
