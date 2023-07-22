package com.example.tadak.user.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
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

    public static SocialType getSocialTypeBySocialName(String socialName) {
        return socialNameToTypeMap.get(socialName);
    }
}
