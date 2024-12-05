package com.agile.wahanalk_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "users")
public class User {

  @Id
  private String id;
  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private String email;
  private String password;

  // Getters and Setters
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public LocalDate getBirthday() { return birthday; }
  public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}

