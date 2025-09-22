package com.digital.diary.backend.api;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digital.diary.backend.responses.post.PostImageResponse;
import com.digital.diary.backend.services.PostImageService;

@RestController
@RequestMapping("/api/user/postImages")
public class PostImagesController {

    private final PostImageService postImageService;

    public PostImagesController(PostImageService postImageService) {
        this.postImageService = postImageService;
    }

    @PostMapping("/upload/{postId}")
    public ResponseEntity<PostImageResponse> upload(@RequestPart("image") MultipartFile file, @PathVariable int postId)
            throws IOException {
        PostImageResponse postImageResponse = postImageService.upload(file, postId);
        return new ResponseEntity<>(postImageResponse, HttpStatus.OK);
    }

    @GetMapping("/download/{postId}")
    public ResponseEntity<?> download(@PathVariable int postId) {
        byte[] image = postImageService.download(postId);
        if (image != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}