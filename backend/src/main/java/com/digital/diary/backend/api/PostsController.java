package com.digital.diary.backend.api;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.requests.PostAddRequest;
import com.digital.diary.backend.responses.post.PostCardResponse;
import com.digital.diary.backend.responses.post.PostResponse;
import com.digital.diary.backend.services.PostService;

@RestController
@RequestMapping("/api/user/posts")
public class PostsController {

    private final PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAllByUserFollowing")
    public ResponseEntity<List<PostCardResponse>> getAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{postId}")
    public ResponseEntity<PostResponse> getById(@PathVariable int postId) {
        return new ResponseEntity<>(postService.getById(postId), HttpStatus.OK);
    }

    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<List<PostResponse>> getAllByUser(@PathVariable int userId) {
        return new ResponseEntity<>(postService.getAllByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/getAllByMyself")
    public ResponseEntity<List<PostCardResponse>> getAllByMyself() {
        return new ResponseEntity<>(postService.getAllByMyself(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody PostAddRequest postAddRequest) throws IOException {
        int postId = postService.add(postAddRequest);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete() {
        postService.delete();
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
