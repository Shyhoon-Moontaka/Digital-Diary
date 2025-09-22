package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;

import com.digital.diary.backend.models.UserImage;
import com.digital.diary.backend.responses.user.UserImageResponse;

@Mapper(componentModel = "spring")
public interface UserImageMapper {

    UserImageResponse userImageToResponse(UserImage userImage);

}
