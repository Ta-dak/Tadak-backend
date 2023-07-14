package com.example.tadak.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequestDto {
    String nickname;
    String email;
    @JsonProperty("login_type")
    String loginType;
}
