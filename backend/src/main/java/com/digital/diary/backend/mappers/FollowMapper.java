package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.digital.diary.backend.models.Follow;
import com.digital.diary.backend.requests.FollowRequest;
import com.digital.diary.backend.responses.follow.FollowResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Mapping(source = "followingId", target = "following.id")
    Follow addRequestToFollow(FollowRequest followAddRequest);

    @Mapping(source = "following.firstName", target = "firstName")
    @Mapping(source = "following.lastName", target = "lastName")
    FollowResponse followToResponse(Follow follow);

    List<FollowResponse> followsToResponses(List<Follow> following);
}
