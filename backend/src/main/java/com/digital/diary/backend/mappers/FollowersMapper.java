package com.digital.diary.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.digital.diary.backend.models.Follow;
import com.digital.diary.backend.responses.follow.FollowersResponse;

@Mapper(componentModel = "spring")
public interface FollowersMapper {

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.id", target = "id")
    FollowersResponse followToResponse(Follow follow);

    List<FollowersResponse> followsToResponses(List<Follow> following);
}
