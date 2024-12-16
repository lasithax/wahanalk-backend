package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.dto.ResetPasswordRequest;
import com.agile.wahanalk_backend.model.User;
import com.agile.wahanalk_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // private BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/test")
  public String test() {
    return "Test executed successfully.";
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

  // New login endpoint
  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
    Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      // Verify password (consider adding hashing in future)
      if (user.getPassword().equals(loginRequest.getPassword())) {
        return ResponseEntity.status(200).body("200");
      } else {
        return ResponseEntity.status(401).body("Invalid password.");
      }
    } else {
      return ResponseEntity.status(404).body("User not found.");
    }
  }

  @GetMapping("/{email}")
  public ResponseEntity<?> viewProfile(@PathVariable String email) {
    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return ResponseEntity.ok(user); // Returns the full user object
    } else {
      return ResponseEntity.status(404).body("User not found.");
    }
  }

  @PostMapping("/reset-password")
  public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
    Optional<User> optionalUser = userRepository.findByEmail(resetPasswordRequest.getEmail());

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();

      // Verify current password (if required)
      if (resetPasswordRequest.getCurrentPassword() != null &&
        !user.getPassword().equals(resetPasswordRequest.getCurrentPassword())) {
        return ResponseEntity.status(401).body("Invalid current password.");
      }

      // Update password
      user.setPassword(resetPasswordRequest.getNewPassword());
      userRepository.save(user);

      return ResponseEntity.ok("Password reset successfully.");
    } else {
      return ResponseEntity.status(404).body("User not found.");
    }
  }

  //Update user by email
  @PostMapping("/update-user")
  public ResponseEntity<String> updateUser(@RequestBody User updateUserRequest) {
    Optional<User> optionalUser = userRepository.findByEmail(updateUserRequest.getEmail());

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();

      // Update the fields provided in the request
      if (updateUserRequest.getFirstName() != null) {
        user.setFirstName(updateUserRequest.getFirstName());
      }
      if (updateUserRequest.getLastName() != null) {
        user.setLastName(updateUserRequest.getLastName());
      }
      if (updateUserRequest.getBirthday() != null) {
        user.setBirthday(updateUserRequest.getBirthday());
      }

      userRepository.save(user);
      return ResponseEntity.ok("User updated successfully.");
    } else {
      return ResponseEntity.status(404).body("User not found.");
    }
  }
}

