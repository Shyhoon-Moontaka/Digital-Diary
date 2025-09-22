package com.digital.diary.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.requests.FollowRequest;
import com.digital.diary.backend.services.FollowService;
import com.digital.diary.backend.services.UserService;

@RestController
@RequestMapping("/api/user/follows")
public class FollowsController {

    private final FollowService followService;

    private final UserService userService;

    public FollowsController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody FollowRequest followRequest) {
        followService.add(followRequest);
        return new ResponseEntity<>("Followed", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody FollowRequest followRequest) {
        followService.delete(followRequest.getFollowingId());
        return new ResponseEntity<>("Unfollowed", HttpStatus.OK);
    }

    @GetMapping("/isFollowing/{followingId}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable int followingId) {
        return new ResponseEntity<>(userService.isFollowing(followingId), HttpStatus.OK);
    }
}
