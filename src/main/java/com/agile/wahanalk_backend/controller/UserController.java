package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.User;
import com.agile.wahanalk_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // private BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/test")
  public String test() {
    return "User registered successfully.";
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().body("Email already in use.");
    }
    // Encrypt password before saving
    user.setPassword(user.getPassword());
    userRepository.save(user);
    return ResponseEntity.ok("User registered successfully.");
  }
}
