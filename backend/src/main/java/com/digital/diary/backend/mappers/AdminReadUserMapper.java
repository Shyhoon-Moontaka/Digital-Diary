package com.digital.diary.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.responses.admin.ReadUserResponse;

@Mapper(componentModel = "spring")
public interface AdminReadUserMapper {
    ReadUserResponse userToResponse(Users user);

    List<ReadUserResponse> usersToResponses(List<Users> users);
}
