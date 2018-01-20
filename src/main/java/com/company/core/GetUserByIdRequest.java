package com.company.core;

import com.company.model.User;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class GetUserByIdRequest {
    private static long userId;

    private static ResponseEntity<User> getUserById() {


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

        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("userID", String.valueOf(userId));
        return restTemplate.exchange(properties.getProperty("urlUser"), HttpMethod.GET,
                entity, User.class, urlParams);
    }

    public static boolean checkUser(long id){
        userId = id;
        ResponseEntity<User> response = getUserById();
        if (response.getStatusCodeValue() == 200) {
            if (response.getBody() == null) {
                System.out.println("No such user.");
                return false;
            }
        } else {
            System.out.println(response.getStatusCode().name());
            return false;
        }
        return true;
    }

    public static void printResponce() {
        setFields();
        ResponseEntity<User> response = getUserById();
        if (response.getStatusCodeValue() == 200) {
            if (response.getBody() != null) {
                System.out.println(response.getBody());
            } else {
                System.out.println("No such user.");
            }
        } else {
            System.out.println(response.getStatusCode().name());
        }
    }

    @SneakyThrows
    private static void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input user ID: ");
        while (flag) {
            String str = bufferedReader.readLine();
            try {
                userId = Long.parseLong(str);
                flag = false;
            } catch (NumberFormatException e) {
                System.err.println("Input number!");
                flag = true;
            }
        }

    }


}