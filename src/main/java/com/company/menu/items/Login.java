package com.company.menu.items;

import com.company.core.LoginRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import org.springframework.web.client.HttpClientErrorException;

public class Login extends Item{


    public Login(InputOutput inputOutput) {
        super(inputOutput);
    }

    public String displayedName() {
        return "Login";
    }

    public void perform() {
        try {
            LoginRequest.printResponce();
        }catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}