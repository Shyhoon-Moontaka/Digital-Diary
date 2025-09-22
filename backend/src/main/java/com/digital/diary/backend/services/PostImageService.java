package com.digital.diary.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digital.diary.backend.mappers.PostImageMapper;
import com.digital.diary.backend.models.Post;
import com.digital.diary.backend.models.PostImages;
import com.digital.diary.backend.repositories.PostImageRepository;
import com.digital.diary.backend.responses.post.PostImageResponse;
import com.digital.diary.backend.utils.UserInfoFromJwt;

import io.jsonwebtoken.io.IOException;

@Service
public class PostImageService {

    private final PostImageRepository postImageRepository;
    private final PostImageMapper postImageMapper;

    public PostImageService(UserInfoFromJwt userInfoFromJwt, PostImageRepository postImageRepository,
            PostImageMapper postImageMapper) {
        this.postImageRepository = postImageRepository;
        this.postImageMapper = postImageMapper;
    }

    public PostImageResponse upload(MultipartFile file, int postId) throws IOException, java.io.IOException {
        PostImages postImage = new PostImages();
        postImage.setData(file.getBytes());
        Post post = new Post();
        post.setId(postId);
        postImage.setPost(post);
        postImageRepository.save(postImage);
        return postImageMapper.postImageToResponse(postImage);
    }

    public byte[] download(int postId) {
        PostImages postImage = postImageRepository.findByPostId(postId);
        return postImage.getData();
    }
}