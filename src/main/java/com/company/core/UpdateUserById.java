package com.company.core;

import com.company.model.User;
import com.company.model.dto.UserUpdateRequest;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateUserById {

    private static long userId;
    private static String firstName;
    private static String lastName;
    private static String email;

    private static ResponseEntity<User> updateUserById() {


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
        HttpEntity entityCheck = new HttpEntity(headers);
        Map<String, String> urlParams = new LinkedHashMap<>();

        urlParams.put("userID", String.valueOf(userId));

        HttpEntity<UserUpdateRequest> entity = new HttpEntity(new UserUpdateRequest(firstName, lastName, email), headers);

        return restTemplate.exchange(properties.getProperty("urlUser"), HttpMethod.PUT,
                entity, User.class, urlParams);

    }


    public static void printResponce() {
        setID();
        if(!GetUserByIdRequest.checkUser(userId)){
            return;
        }
        setFields();
        ResponseEntity<User> response = updateUserById();
        if (response == null) {
            System.out.println("No such user");
            return;
        }
        if (response.getStatusCodeValue() == 200) {
            if (response.getBody() != null) {
                System.out.println(response.getBody());
            } else {
                System.out.println("No such user.");
            }
        } else if (response.getStatusCodeValue() == 500) {
            System.out.println("Probably no such user!");
        } else {
            System.out.println(response.getStatusCode().name());
        }

    }

    @SneakyThrows
    private static void setID() {
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

    @SneakyThrows
    private static void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input new firstname (3-50 letters):");
        while (flag) {
            firstName = bufferedReader.readLine();
            if (firstName.length() >= 3 && firstName.length() <= 50) {
                flag = false;
            } else {
                if (firstName.length() == 0) {
                    System.err.println("Firstname shouldn't be blank!");
                } else {
                    System.err.println("Firstname should be 3 - 50 letters!");
                }
            }
        }
        flag = true;
        System.out.println("Input new lastname (3-50 letters):");
        while (flag) {
            lastName = bufferedReader.readLine();
            if (lastName.length() >= 3 && lastName.length() <= 50) {
                flag = false;
            } else {
                if (lastName.length() == 0) {
                    System.err.println("Lastname shouldn't be blank!");
                } else {
                    System.err.println("Lastname should be 3 - 50 letters!");
                }
            }
        }
        flag = true;
        System.out.println("Input new email (should match example@example.com):");
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

    }


}