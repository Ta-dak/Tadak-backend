package com.example.tadak.manager.service;

import com.example.tadak.util.CustomException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.tadak.util.ResponseCode.FORBIDDEN_TOKEN_NOT_VALID;
import static com.example.tadak.util.ResponseCode.SERVER_ERROR_CONNECTION;


@Service
@RequiredArgsConstructor
public class ManagerService {

    @Value("${oauth2.kakao.rest-api}")
    private String restAPIKey;

    public String getKakaoAccessToken(String controllerCode) {
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String accessToken = "";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + restAPIKey);
            sb.append("&redirect_uri=http://localhost:8080/manager/auth/kakao");
            sb.append("&code=" + controllerCode);
            bw.write(sb.toString());
            bw.flush();
            bw.close();

            JsonElement element = JsonParser.parseString(getConnectionResponse(conn.getInputStream()));
            accessToken = element.getAsJsonObject().get("access_token").getAsString();

        } catch (IOException e) {
            throw new CustomException(SERVER_ERROR_CONNECTION);
        }

        return accessToken;
    }

    public String getUserInfo(String accessToken) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String response;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            JsonElement element = JsonParser.parseString(getConnectionResponse(conn.getInputStream()));
            response = element.getAsJsonObject().toString();
        } catch (IOException e) {
            throw new CustomException(FORBIDDEN_TOKEN_NOT_VALID);
        }

        return response;
    }


    private String getConnectionResponse(InputStream inputStream) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }

        br.close();

        return result;
    }
}
