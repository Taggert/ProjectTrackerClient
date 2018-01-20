package com.company.menu.items;

import com.company.core.AllUsersRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import org.springframework.web.client.HttpClientErrorException;

public class AllUsers extends Item {


    public AllUsers(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Show all users";
    }

    @Override
    public void perform() {
        try {
            AllUsersRequest.printResponse();
        }catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}