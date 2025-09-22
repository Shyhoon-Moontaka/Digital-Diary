package com.digital.diary.backend.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.requests.LikeRequest;
import com.digital.diary.backend.responses.like.LikeResponse;
import com.digital.diary.backend.services.LikeService;

@RestController
@RequestMapping("/api/user/likes")
public class LikesController {

    private final LikeService likeService;

    public LikesController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody LikeRequest likeRequest) {
        likeService.add(likeRequest);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    @GetMapping("/getallbypost/{postId}")
    public ResponseEntity<List<LikeResponse>> getAllByPost(@PathVariable int postId) {
        return new ResponseEntity<>(likeService.getAllByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/isliked/{postId}")
    public ResponseEntity<Boolean> isLiked(@PathVariable int postId) {
        return new ResponseEntity<>(likeService.isLiked(postId), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody LikeRequest likeRequest) {
        likeService.delete(likeRequest);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
