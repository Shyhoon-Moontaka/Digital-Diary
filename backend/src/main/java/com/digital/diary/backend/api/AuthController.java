package com.digital.diary.backend.api;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.repositories.UserRepository;
import com.digital.diary.backend.requests.LoginRequest;
import com.digital.diary.backend.requests.RegisterRequest;
import com.digital.diary.backend.security.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            HashMap<String, String> hash = new HashMap<>();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            Users user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            String token = jwtUtil.generateToken(
                    user.getEmail(),
                    user.getId(),
                    user.getFirstName() + " " + user.getLastName());

            hash.put("token", token);
            hash.put("userId", Integer.toString(user.getId()));

            return new ResponseEntity<>(hash, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        userRepository.save(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));

        String token = jwtUtil.generateToken(
                registerRequest.getEmail(),
                userRepository.findByEmail(registerRequest.getEmail()).getId(),
                registerRequest.getFirstName() + " " + registerRequest.getLastName());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
