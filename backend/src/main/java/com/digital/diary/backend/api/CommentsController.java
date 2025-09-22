package com.digital.diary.backend.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.requests.CommentAddRequest;
import com.digital.diary.backend.requests.CommentUpdateRequest;
import com.digital.diary.backend.responses.comment.CommentGetResponse;
import com.digital.diary.backend.services.CommentService;

@RestController
@RequestMapping("/api/user/comments")
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CommentGetResponse>> getAll() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getallbypost/{postId}")
    public ResponseEntity<List<CommentGetResponse>> getAllByPost(@PathVariable int postId) {
        return new ResponseEntity<>(commentService.getAllByPost(postId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CommentAddRequest commentAddRequest) {
        commentService.add(commentAddRequest);
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.update(id, commentUpdateRequest);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id) {
        commentService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
