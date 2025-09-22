package com.digital.diary.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.digital.diary.backend.models.Follow;
import com.digital.diary.backend.responses.follow.FollowingResponse;

@Mapper(componentModel = "spring")
public interface FollowingMapper {

    @Mapping(source = "following.firstName", target = "firstName")
    @Mapping(source = "following.lastName", target = "lastName")
    @Mapping(source = "following.id", target = "id")
    FollowingResponse followToResponse(Follow follow);

    List<FollowingResponse> followsToResponses(List<Follow> following);
}
