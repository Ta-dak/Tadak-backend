package com.example.tadak.auth.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Data
@Component
@ConstructorBinding
@ConfigurationProperties(prefix="oauth2.google")
public class GoogleProperty {
    private String tokenUri;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
