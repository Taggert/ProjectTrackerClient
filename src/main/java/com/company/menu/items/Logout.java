package com.company.menu.items;

import com.company.core.LogoutRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import org.springframework.web.client.HttpClientErrorException;

public class Logout extends Item {


    public Logout(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Logout";
    }

    @Override
    public void perform() {
        try {
            LogoutRequest.printResponce();
        }catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}