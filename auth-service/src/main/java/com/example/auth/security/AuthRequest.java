package com.example.auth.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String firstName;
    @NotBlank(message = "User cannot be null")
    private String username;
    @NotBlank(message = "Password cannot be null")
    private String password;

}
