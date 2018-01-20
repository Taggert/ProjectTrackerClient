package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistarionRequest {

   /* @NotBlank(message = "First name cannot be blank")
    @Length(min = 3, max = 50, message = "First name's length should be 3 to 50 characters")*/
    private String firstName;

   /* @NotBlank(message = "Last name cannot be blank")
    @Length(min = 3, max = 50, message = "Last name's length should be 3 to 50 characters")*/
    private String lastName;

    /*@Email(message = "Email format is incorrect")
    @NotBlank(message = "Email cannot be blank")*/
    private String email;

 /*   @NotBlank(message = "Password cannot be blank")
    @Length(min = 6, max = 25, message = "Password's length should be 6 to 25 characters")*/
    private String password;

}