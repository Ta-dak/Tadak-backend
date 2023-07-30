package com.example.tadak.auth.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleUserDto {
    private String sub;
    private String email;
    private Boolean emailVerified;
    private String picture;
}
