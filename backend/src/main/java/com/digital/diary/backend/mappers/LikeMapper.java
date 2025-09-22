package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.digital.diary.backend.models.Likes;
import com.digital.diary.backend.requests.LikeRequest;
import com.digital.diary.backend.responses.like.LikeResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    LikeResponse likeToPostLikeResponse(Likes like);

    @Mapping(source = "postId", target = "post.id")
    Likes requestToLike(LikeRequest likeRequest);

    List<LikeResponse> likesToLikeResponses(List<Likes> likes);
}
