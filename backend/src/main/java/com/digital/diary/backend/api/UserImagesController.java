package com.digital.diary.backend.api;

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

import com.digital.diary.backend.repositories.UserImageRepository;
import com.digital.diary.backend.services.UserImageService;

@RestController
@RequestMapping("/api/user/userimages")
public class UserImagesController {

    private final UserImageService userImageService;

    public UserImagesController(UserImageService userImageService, UserImageRepository userImageRepository) {
        this.userImageService = userImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart("image") MultipartFile file)
            throws Exception {
        userImageService.upload(file);
        return new ResponseEntity<>("Uploaded", HttpStatus.OK);
    }

    @GetMapping("/downloadOther/{userId}")
    public ResponseEntity<?> downloadOther(@PathVariable int userId) {
        byte[] image = userImageService.downloadOther(userId);
        if (image != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @GetMapping("/downloadMine")
    public ResponseEntity<?> downloadMine() {
        byte[] image = userImageService.downloadMine();
        if (image != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
}