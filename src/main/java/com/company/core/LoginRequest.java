package com.company.core;

import com.company.model.dto.UserLoginRequest;
import com.company.model.dto.UserRegistrationAndLoginResponce;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginRequest {

    private static String email = "";
    private static String password = "";

    private static ResponseEntity<UserRegistrationAndLoginResponce> logIn() {
        setFields();

        RestTemplate restTemplate = new RestTemplate();

        Properties properties = new Properties();
        InputStream is = RegisterRequest.class.getResourceAsStream("/app.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.err.println("Something wrong with property file");
        }
        ResponseEntity<UserRegistrationAndLoginResponce> response = restTemplate.postForEntity(
                properties.getProperty("urlLogin"), new UserLoginRequest(email, password),
                UserRegistrationAndLoginResponce.class);
        String sessionId = response.getBody().getSessionId();
        System.setProperty("SESSION_ID", sessionId);
        return response;
    }

    public static void printResponce() {
        ResponseEntity<UserRegistrationAndLoginResponce> response = logIn();
        if (response.getStatusCodeValue() == 200) {
            System.out.println("User " + response.getBody().getUser().getFirstName() + " "
                    + response.getBody().getUser().getLastName() + " is logged in.\nSession ID is"+System.getProperty("SESSION_ID"));
        } else {
            System.out.println(response.getStatusCode().name());
        }
    }

    @SneakyThrows
    private static void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input email (should match example@example.com):");
        while (flag) {
            email = bufferedReader.readLine();
            Pattern p = Pattern.compile("\\w+\\@[0-9a-zA-Z]+\\.[a-zA-Z]+");
            Matcher m = p.matcher(email);
            if (m.matches()) {
                flag = false;
            } else {
                if (email.length() == 0) {
                    System.err.println("Email shouldn't be blank!");
                } else {
                    System.err.println("Email should match example!");
                }
            }
        }
        flag = true;
        System.out.println("Input password (6-25 symbols):");
        while (flag) {
            password = bufferedReader.readLine();
            if (password.length() >= 6 && password.length() <= 20) {
                flag = false;
            } else {
                if (password.length() == 0) {
                    System.err.println("Password shouldn't be blank!");
                } else {
                    System.err.println("Password should be 6 - 20 letters!");
                }
            }
        }

    }


}