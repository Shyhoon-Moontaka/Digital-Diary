package com.digital.diary.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.digital.diary.backend.models.Reply;
import com.digital.diary.backend.requests.ReplyRequest;
import com.digital.diary.backend.responses.reply.ReplyResponse;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    ReplyResponse replyToResponse(Reply reply);

    List<ReplyResponse> repliesToResponses(List<Reply> replies);

    @Mapping(source = "commentId", target = "commentId")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "description", target = "description")
    Reply addRequestToReply(ReplyRequest replyRequest);
}
