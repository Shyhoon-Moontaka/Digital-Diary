package com.digital.diary.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.digital.diary.backend.mappers.AdminReadUserMapper;
import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.repositories.CommentRepository;
import com.digital.diary.backend.repositories.LikeRepository;
import com.digital.diary.backend.repositories.NotificationRepository;
import com.digital.diary.backend.repositories.PostRepository;
import com.digital.diary.backend.repositories.UserRepository;
import com.digital.diary.backend.responses.admin.ReadUserResponse;
import com.digital.diary.backend.security.JwtUtil;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminReadUserMapper adminReadUserMapper;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody Users users) {
        try {
            HashMap<String, String> hash = new HashMap<>();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            users.getEmail(),
                            users.getPassword()));

            Users admin = userRepository.findByEmail(users.getEmail());
            if (admin == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            String token = jwtUtil.generateToken(
                    admin.getEmail(),
                    admin.getId(),
                    admin.getFirstName() + " " + admin.getLastName());

            hash.put("token", token);
            hash.put("adminId", Integer.toString(admin.getId()));

            return new ResponseEntity<>(hash, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users users) {
        if (userRepository.findByEmail(users.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Users admin = new Users();
        admin.setEmail(users.getEmail());
        admin.setFirstName(users.getFirstName());
        admin.setLastName(users.getLastName());
        admin.setPassword(passwordEncoder.encode(users.getPassword()));
        admin.setRole(users.getRole());
        userRepository.save(admin);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));

        String token = jwtUtil.generateToken(
                admin.getEmail(),
                userRepository.findByEmail(admin.getEmail()).getId(),
                admin.getFirstName() + " " + admin.getLastName());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<ReadUserResponse> createUser(@RequestBody Users user) {
        Users savedUser = new Users();
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        savedUser.setRole(user.getRole());
        userRepository.save(savedUser);
        return ResponseEntity.ok(adminReadUserMapper.userToResponse(savedUser));
    }

    @GetMapping("/users")
    public ResponseEntity<List<ReadUserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminReadUserMapper.usersToResponses(userRepository.findAll()));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Users user) {
        Users existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setRole(user.getRole());
            userRepository.save(existingUser);
            return ResponseEntity.ok(adminReadUserMapper.userToResponse(existingUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/statistics")
    public ResponseEntity<HashMap<String, Long>> getStatistics() {
        HashMap<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalPosts", postRepository.count());
        stats.put("totalComments", commentRepository.count());
        stats.put("totalLikes", likeRepository.count());
        stats.put("totalNotifications", notificationRepository.count());
        return ResponseEntity.ok(stats);
    }
}
