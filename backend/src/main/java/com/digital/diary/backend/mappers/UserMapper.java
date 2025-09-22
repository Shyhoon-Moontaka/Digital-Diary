package com.digital.diary.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.responses.user.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToResponse(Users user);

    List<UserResponse> usersToResponses(List<Users> users);

}
