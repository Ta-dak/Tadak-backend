package com.example.tadak.auth.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleUserInfo {
    private String sub;
    private String email;
    private Boolean emailVerified;
    private String name;
    private String givenName;
    private String familyName;
    private String picture;
    private String locale;
}
