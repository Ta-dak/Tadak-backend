package com.example.tadak.auth.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserDto {
    private String sub;
    private String email;
    private Boolean emailVerified;
    private String picture;
}
