package com.digital.diary.backend.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.responses.follow.FollowersResponse;
import com.digital.diary.backend.responses.follow.FollowingResponse;
import com.digital.diary.backend.responses.user.UserResponse;
import com.digital.diary.backend.services.FollowService;
import com.digital.diary.backend.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    private final UserService userService;

    private final FollowService followService;

    public UsersController(UserService userService, FollowService followService) {
        this.userService = userService;
        this.followService = followService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserResponse>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOtherProfile/{id}")
    public ResponseEntity<UserResponse> getOtherProfile(@PathVariable int id) {
        return new ResponseEntity<>(userService.getOtherProfileData(id), HttpStatus.OK);
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<UserResponse> getMyProfile() {
        return new ResponseEntity<>(userService.getMyProfileData(), HttpStatus.OK);
    }

    @GetMapping("/getFollowers")
    public ResponseEntity<List<FollowersResponse>> getFollwers() {
        return new ResponseEntity<>(followService.getFollwers(), HttpStatus.OK);
    }

    @GetMapping("/getFollowing")
    public ResponseEntity<List<FollowingResponse>> getFollowing() {
        return new ResponseEntity<>(followService.getFollowing(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        userService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
