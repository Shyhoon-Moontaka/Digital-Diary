package com.digital.diary.backend.services;

import org.springframework.stereotype.Service;
import com.digital.diary.backend.mappers.CommentMapper;
import com.digital.diary.backend.models.Comment;
import com.digital.diary.backend.models.Post;
import com.digital.diary.backend.repositories.CommentRepository;
import com.digital.diary.backend.repositories.PostRepository;
import com.digital.diary.backend.requests.CommentAddRequest;
import com.digital.diary.backend.requests.CommentUpdateRequest;
import com.digital.diary.backend.responses.comment.CommentGetResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

import java.util.List;

@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserInfoFromJwt userInfoFromJwt;
    private final NotificationService notificationService;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
            UserInfoFromJwt userInfoFromJwt, NotificationService notificationService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userInfoFromJwt = userInfoFromJwt;
        this.notificationService = notificationService;
        this.postRepository = postRepository;
    }

    public void add(CommentAddRequest commentAddRequest) {
        Comment comment = commentMapper.addRequestToComment(commentAddRequest);
        comment.setUser(userInfoFromJwt.getMineFromToken());
        commentRepository.save(comment);
        Post post = postRepository.findById(comment.getPost().getId()).orElse(null);
        String fullName = userInfoFromJwt.getMineFromToken().getFirstName()
                + " " + userInfoFromJwt.getMineFromToken().getLastName();
        notificationService.add(
                fullName
                        + " has commented on your post.Wanna see?",
                "post/" + comment.getPost().getId(), post.getUser().getId());
    }

    public List<CommentGetResponse> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.commentsToResponses(comments);
    }

    public List<CommentGetResponse> getAllByPost(int postId) {
        List<Comment> comments = commentRepository.findAllByPost_Id(postId);
        return commentMapper.commentsToResponses(comments);
    }

    public void update(int id, CommentUpdateRequest commentUpdateRequest) {
        Comment commentToUpdate = commentRepository.findById(id).orElse(null);
        if (commentToUpdate != null) {
            commentToUpdate.setDescription(commentUpdateRequest.getDescription());
            commentRepository.save(commentToUpdate);
        }
    }

    public void delete(int id) {
        commentRepository.deleteById(id);
    }
}
