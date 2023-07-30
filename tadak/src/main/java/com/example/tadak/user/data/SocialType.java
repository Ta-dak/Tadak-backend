package com.example.tadak.user.data;

import com.example.tadak.util.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.tadak.util.ResponseCode.BAD_REQUEST_INVALID_LOGIN_TYPE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SocialType {
    GOOGLE(
            "google",
            "https://www.googleapis.com/oauth2/v3/userinfo",
            HttpMethod.GET
    ),

    KAKAO(
            "kakao",
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST
    );

    private String socialName;
    private String userInfoUrl;
    private HttpMethod method;

    private static final Map<String, SocialType> socialNameToTypeMap = new HashMap<>();
    static {
        for (SocialType socialType : SocialType.values()) {
            socialNameToTypeMap.put(socialType.socialName, socialType);
        }
    }

    public static SocialType findBySocialName(String socialName) {
        try {
            return socialNameToTypeMap.get(socialName);
        } catch (NullPointerException e) {
            throw new CustomException(BAD_REQUEST_INVALID_LOGIN_TYPE);
        }
    }
}
