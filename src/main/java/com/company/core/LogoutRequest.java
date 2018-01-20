package com.company.core;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LogoutRequest {

    private static ResponseEntity<String> logOut() {
        RestTemplate restTemplate = new RestTemplate();

        Properties properties = new Properties();
        InputStream is = RegisterRequest.class.getResourceAsStream("/app.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.err.println("Something wrong with property file");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(properties.getProperty("urlLogout"), HttpMethod.POST,
                entity , String.class);
        System.clearProperty(System.getProperty("SESSION_ID"));
        return response;
    }

    public static void printResponce() {
        ResponseEntity<String> response = logOut();
        if (response.getStatusCodeValue() == 200) {
            System.out.println("User is logged out");
        } else {
            System.out.println(response.getStatusCode().name());
        }
    }

}