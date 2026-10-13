package com.mediaHub.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    @NotBlank(message = "Email or username is required.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8,message = "Password should least contain 8 characters.")
    private String password;
}
