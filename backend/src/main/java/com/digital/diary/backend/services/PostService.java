package com.digital.diary.backend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digital.diary.backend.mappers.PostsMapper;
import com.digital.diary.backend.models.Follow;
import com.digital.diary.backend.models.Post;
import com.digital.diary.backend.repositories.FollowRepository;
import com.digital.diary.backend.repositories.PostRepository;
import com.digital.diary.backend.requests.PostAddRequest;
import com.digital.diary.backend.responses.post.PostCardResponse;
import com.digital.diary.backend.responses.post.PostResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostsMapper postMapper;
    private final FollowRepository followRepository;
    private final UserInfoFromJwt userInfoFromJwt;
    private final NotificationService notificationService;

    public PostService(UserInfoFromJwt userInfoFromJwt, PostRepository postRepository, PostsMapper postMapper,
            FollowRepository followRepository, NotificationService notificationService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.followRepository = followRepository;
        this.userInfoFromJwt = userInfoFromJwt;
        this.notificationService = notificationService;
    }

    public List<PostCardResponse> getAll() {
        List<Follow> following = followRepository.findAllByUser_Id(userInfoFromJwt.getMineFromToken().getId());
        List<Post> posts = new ArrayList<>();
        for (Follow fl : following) {
            posts.addAll(postRepository.findAllByUser_IdOrderByIdDesc(fl.getFollowing().getId()));
        }
        posts.sort(Comparator.comparing(Post::getId).reversed());
        return postMapper.postCardsToGetResponses(posts);
    }

    public List<PostResponse> getAllByUser(int userId) {
        List<Post> userPosts = postRepository.findAllByUser_IdOrderByIdDesc(userId);
        return postMapper.postsToGetResponses(userPosts);
    }

    public List<PostCardResponse> getAllByMyself() {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        List<Post> userPosts = postRepository.findAllByUser_IdOrderByIdDesc(userId);
        return postMapper.postCardsToGetResponses(userPosts);
    }

    public PostResponse getById(int postId) {
        Post userPosts = postRepository.findById(postId).orElse(null);
        return postMapper.postToGetResponse(userPosts);
    }

    public int add(PostAddRequest postAddRequest) throws IOException {
        Post post = postMapper.postAddRequestToPost(postAddRequest);
        post.setUser(userInfoFromJwt.getMineFromToken());
        postRepository.save(post);
        int userId = userInfoFromJwt.getMineFromToken().getId();
        List<Follow> followers = followRepository.findByFollowing_Id(userId);
        if (!followers.isEmpty()) {
            for (Follow fl : followers) {
                notificationService.add("A new post has been added to your timeline...",
                        "post/" + post.getId(), fl.getUser().getId());
            }
        }
        return post.getId();
    }

    public void delete() {
        postRepository.deleteById(userInfoFromJwt.getMineFromToken().getId());
    }
}
