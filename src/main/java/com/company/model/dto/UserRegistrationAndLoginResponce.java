package com.company.model.dto;

import com.company.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationAndLoginResponce {

    private String sessionId;
    private User user;

}