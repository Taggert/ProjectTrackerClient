package com.company.core;

import com.company.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AllUsersRequest {

    private static ResponseEntity<User[]> getResponce() {
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

        return restTemplate.exchange(properties.getProperty("urlUsers"), HttpMethod.GET, entity,
                User[].class);
    }

    public static void printResponse() {
        ResponseEntity<User[]> response = getResponce();

        System.out.println("Users in the base:\n");
        for (User user : response.getBody()) {
            System.out.println(user);
        }
    }


}