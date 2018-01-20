package com.company.menu.items;

import com.company.core.RegisterRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import org.springframework.web.client.HttpClientErrorException;

public class Register extends Item {


    public Register(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Register new user";
    }

    @Override
    public void perform() {
        try {
            RegisterRequest.printResponce();
        }catch (HttpClientErrorException e){
            System.err.println(e.getMessage());
            return;
        }
    }
}