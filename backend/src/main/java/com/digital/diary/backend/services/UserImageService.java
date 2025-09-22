package com.digital.diary.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digital.diary.backend.mappers.UserImageMapper;
import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.models.UserImage;
import com.digital.diary.backend.repositories.UserImageRepository;
import com.digital.diary.backend.utils.UserInfoFromJwt;

@Service
public class UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserInfoFromJwt userInfoFromJwt;

    public UserImageService(UserInfoFromJwt userInfoFromJwt, UserImageRepository userImageRepository,
            UserService userService,
            UserImageMapper userImageMapper) {
        this.userImageRepository = userImageRepository;
        this.userInfoFromJwt = userInfoFromJwt;
    }

    public void upload(MultipartFile file) throws Exception {
        int userId = userInfoFromJwt.getMineFromToken().getId();
        UserImage existingUserImage = userImageRepository.findByUserId(userId);
        if (existingUserImage != null) {
            existingUserImage.setData(file.getBytes());
            userImageRepository.save(existingUserImage);
        } else {
            UserImage userImage = new UserImage();
            userImage.setData(file.getBytes());
            Users user = new Users();
            user.setId(userId);
            userImage.setUser(user);
            userImageRepository.save(userImage);
        }
    }

    public byte[] downloadOther(int userId) {
        UserImage userImage = userImageRepository.findByUserId(userId);
        return userImage.getData();
    }

    public byte[] downloadMine() {
        UserImage userImage = userImageRepository.findByUserId(userInfoFromJwt.getMineFromToken().getId());
        return userImage.getData();
    }

}