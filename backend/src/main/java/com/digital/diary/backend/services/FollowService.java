package com.digital.diary.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digital.diary.backend.mappers.FollowMapper;
import com.digital.diary.backend.mappers.FollowersMapper;
import com.digital.diary.backend.mappers.FollowingMapper;
import com.digital.diary.backend.models.Follow;
import com.digital.diary.backend.repositories.FollowRepository;
import com.digital.diary.backend.requests.FollowRequest;
import com.digital.diary.backend.responses.follow.FollowersResponse;
import com.digital.diary.backend.responses.follow.FollowingResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    private final FollowersMapper followersMapper;
    private final FollowingMapper followingMapper;
    private final UserService userService;
    private final UserInfoFromJwt userInfoFromJwt;
    private final NotificationService notificationService;

    public FollowService(FollowRepository followRepository, FollowMapper followMapper, FollowersMapper followersMapper,
            FollowingMapper followingMapper, UserService userService, UserInfoFromJwt userInfoFromJwt,
            NotificationService notificationService) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
        this.followersMapper = followersMapper;
        this.followingMapper = followingMapper;
        this.userService = userService;
        this.userInfoFromJwt = userInfoFromJwt;
        this.notificationService = notificationService;
    }

    public void add(FollowRequest followAddRequest) {
        if (userService.isFollowing(followAddRequest.getFollowingId())
                || userInfoFromJwt.getMineFromToken().getId() == followAddRequest.getFollowingId()) {
            return;
        }
        Follow follow = followMapper.addRequestToFollow(followAddRequest);
        follow.setUser(userInfoFromJwt.getMineFromToken());
        followRepository.save(follow);
        int userId = userInfoFromJwt.getMineFromToken().getId();
        notificationService.add("A new follower has been added to your network.See his/her profile.",
                "profile/" + userId, follow.getFollowing().getId());
    }

    public void delete(int followingId) {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        if (userId == followingId) {
            return;
        }
        Follow follow = followRepository
                .findByUser_IdAndFollowing_Id(userId, followingId)
                .orElse(null);
        if (follow != null) {
            followRepository.deleteById(follow.getId());
        }
    }

    public List<FollowersResponse> getFollwers() {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        List<Follow> followers = followRepository.findByFollowing_Id(userId);
        return followersMapper.followsToResponses(followers);
    }

    public List<FollowingResponse> getFollowing() {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        List<Follow> following = followRepository.findByUser_Id(userId);
        return followingMapper.followsToResponses(following);
    }
}
