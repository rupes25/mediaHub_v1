package com.mediaHub.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Full name is required.")
    @Size(min=5,max = 50,message = "Name must contain 5 to 50 characters only.")
    @Column(nullable = false,length = 50)
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    @Column(nullable = false,length = 254)
    private String email;

    @NotBlank(message = "Username is required.")
    @Size(min = 5,max = 20,message = "Username should be between 5 to 20 characters only.")
    @Column(nullable = false,length = 20)
    private String username;

    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of birth must be in the past.")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Password is required.")
    @Size(min = 8,message = "Password should least contain 8 characters.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

}
