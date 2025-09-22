package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.digital.diary.backend.models.Post;
import com.digital.diary.backend.requests.PostAddRequest;
import com.digital.diary.backend.responses.post.PostCardResponse;
import com.digital.diary.backend.responses.post.PostResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostsMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "authorId")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "createdAt", target = "createdAt")
    PostResponse postToGetResponse(Post post);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "createdAt", target = "createdAt")
    PostCardResponse postCardToGetResponse(Post post);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    Post postAddRequestToPost(PostAddRequest postAddRequest);

    List<PostResponse> postsToGetResponses(List<Post> posts);

    List<PostCardResponse> postCardsToGetResponses(List<Post> posts);
}
