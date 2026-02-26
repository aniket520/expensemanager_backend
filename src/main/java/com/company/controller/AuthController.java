package com.company.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.AuthRequest;
import com.company.dto.AuthResponse;
import com.company.dto.RegisterRequest;
import com.company.entity.User;
import com.company.repository.UserRepository;
import com.company.security.JwtService;
import com.company.security.JwtUtil;
import com.company.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api/auth")
public class AuthController {
	
     private UserRepository userRepository;
     @Autowired
     private AuthenticationManager authenticationManager;
	 
     private final JwtService jwtService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;// Service to load user details

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService, UserRepository userRepository ,BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    } 
    
    
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        // Check if username/email already exists
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email is already in use");
        }

        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already taken");
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setRole("ROLE_USER");// default role
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        try {

            System.out.println("LOGIN ATTEMPT");
            System.out.println("EMAIL: " + authRequest.getEmail());
            System.out.println("PASSWORD: " + authRequest.getPassword());

            User user = userRepository.findByEmail(authRequest.getEmail()).orElse(null);

            if (user == null) {
                System.out.println("USER NOT FOUND");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }

            System.out.println("DB PASSWORD: " + user.getPassword());
            System.out.println("MATCHES: " +
                    passwordEncoder.matches(authRequest.getPassword(), user.getPassword()));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            final UserDetails userDetails =
                    userService.loadUserByUsername(authRequest.getEmail());

            final String token = jwtService.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login failed: " + e.getMessage());
        }}}
    


