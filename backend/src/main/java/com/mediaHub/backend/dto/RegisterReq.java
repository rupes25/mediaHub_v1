package com.mediaHub.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    @NotBlank(message = "Full name is required.")
    @Size(min=5,max = 50,message = "Name must contain 5 to 50 characters only.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    @Size(max = 254, message = "Email is too long.")
    private String email;

    @NotBlank(message = "Username is required.")
    @Size(min = 5,max = 20,message = "Username should be between 5 to 20 characters only.")
    private String username;

    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Password is required.")
    @Size(min = 8,message = "Password should least contain 8 characters.")
    private String password;
}
