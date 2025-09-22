package com.digital.diary.backend.mappers;

import org.mapstruct.Mapper;

import com.digital.diary.backend.models.PostImages;
import com.digital.diary.backend.responses.post.PostImageResponse;

@Mapper(componentModel = "spring")
public interface PostImageMapper {

    PostImageResponse postImageToResponse(PostImages postImage);

}
