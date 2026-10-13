package com.mediaHub.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRes {
    private String fullName;
    private String email;
    private String username;
    private LocalDate dateOfBirth;
    private String message;

}
