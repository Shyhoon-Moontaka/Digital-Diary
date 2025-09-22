package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.digital.diary.backend.models.Comment;
import com.digital.diary.backend.requests.CommentAddRequest;
import com.digital.diary.backend.responses.comment.CommentGetResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    CommentGetResponse commentToResponse(Comment comment);

    List<CommentGetResponse> commentsToResponses(List<Comment> comments);

    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "description", target = "description")
    Comment addRequestToComment(CommentAddRequest commentAddRequest);
}
