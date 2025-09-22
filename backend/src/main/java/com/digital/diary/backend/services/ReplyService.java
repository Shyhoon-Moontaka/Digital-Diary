package com.digital.diary.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digital.diary.backend.mappers.ReplyMapper;
import com.digital.diary.backend.models.Comment;
import com.digital.diary.backend.models.Reply;
import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.repositories.CommentRepository;
import com.digital.diary.backend.repositories.ReplyRepository;
import com.digital.diary.backend.requests.ReplyRequest;
import com.digital.diary.backend.responses.reply.ReplyResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final UserInfoFromJwt userInfoFromJwt;
    private final CommentRepository commentRepository;
    private final NotificationService notificationService;

    public ReplyService(ReplyRepository replyRepository, ReplyMapper replyMapper,
            UserInfoFromJwt userInfoFromJwt, CommentRepository commentRepository,
            NotificationService notificationService) {
        this.replyRepository = replyRepository;
        this.replyMapper = replyMapper;
        this.userInfoFromJwt = userInfoFromJwt;
        this.commentRepository = commentRepository;
        this.notificationService = notificationService;
    }

    public void add(ReplyRequest replyRequest) {
        Reply reply = new Reply();
        reply.setCommentId(replyRequest.getCommentId());
        reply.setDescription(replyRequest.getDescription());
        Users users = new Users();
        users.setId(replyRequest.getUserId());
        reply.setUser(users);
        replyRepository.save(reply);
        Comment comment = commentRepository.findById(reply.getCommentId()).orElse(null);
        String fullName = userInfoFromJwt.getMineFromToken().getFirstName()
                + " " + userInfoFromJwt.getMineFromToken().getLastName();
        notificationService.add(
                fullName
                        + " has replied to your comment.See..",
                "post/" + comment.getPost().getId(), comment.getUser().getId());
    }

    public List<ReplyResponse> getAll(int commentId) {
        List<Reply> replies = replyRepository.findAllByCommentId(commentId);
        return replyMapper.repliesToResponses(replies);
    }

    public void delete(int id) {
        replyRepository.deleteById(id);
    }
}
