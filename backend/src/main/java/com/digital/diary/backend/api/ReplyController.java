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
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.requests.ReplyRequest;
import com.digital.diary.backend.responses.reply.ReplyResponse;
import com.digital.diary.backend.services.ReplyService;

@RestController
@RequestMapping("/api/user/replies")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/get/{commentId}")
    public ResponseEntity<List<ReplyResponse>> getAll(@PathVariable int commentId) {
        return new ResponseEntity<>(replyService.getAll(commentId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody ReplyRequest replyRequest) {
        replyService.add(replyRequest);
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        replyService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
