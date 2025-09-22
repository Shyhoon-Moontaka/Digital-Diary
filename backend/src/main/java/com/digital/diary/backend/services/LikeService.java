package com.digital.diary.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digital.diary.backend.mappers.LikeMapper;
import com.digital.diary.backend.models.Likes;
import com.digital.diary.backend.models.Post;
import com.digital.diary.backend.repositories.FollowRepository;
import com.digital.diary.backend.repositories.LikeRepository;
import com.digital.diary.backend.repositories.PostRepository;
import com.digital.diary.backend.requests.LikeRequest;
import com.digital.diary.backend.responses.like.LikeResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final UserInfoFromJwt userInfoFromJwt;
    private final NotificationService notificationService;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, LikeMapper likeMapper, UserInfoFromJwt userInfoFromJwt,
            NotificationService notificationService,
            FollowRepository followRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
        this.userInfoFromJwt = userInfoFromJwt;
        this.notificationService = notificationService;
        this.postRepository = postRepository;
    }

    public List<LikeResponse> getAllByPost(int postId) {
        List<Likes> likes = likeRepository.findAllByPost_Id(postId);
        return likeMapper.likesToLikeResponses(likes);
    }

    public boolean isLiked(int postId) {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        Optional<Likes> like = likeRepository.findByUser_IdAndPost_Id(userId, postId);
        return like.isPresent();
    }

    public void add(LikeRequest likeRequest) {
        if (isLiked(likeRequest.getPostId())) {
            return;
        }
        Likes like = likeMapper.requestToLike(likeRequest);
        like.setUser(userInfoFromJwt.getMineFromToken());
        likeRepository.save(like);
        Post post = postRepository.findById(like.getPost().getId()).orElse(null);
        String fullName = userInfoFromJwt.getMineFromToken().getFirstName()
                + " " + userInfoFromJwt.getMineFromToken().getLastName();
        notificationService.add(
                fullName + " has liked to your post.See your post.",
                "post/" + like.getPost().getId(), post.getUser().getId());

    }

    public void delete(LikeRequest likeRequest) {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        Optional<Likes> like = likeRepository.findByUser_IdAndPost_Id(userId, likeRequest.getPostId());
        like.ifPresent(likeRepository::delete);
    }
}
