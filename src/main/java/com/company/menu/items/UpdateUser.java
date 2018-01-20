package com.company.menu.items;

import com.company.core.UpdateUserById;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import org.springframework.web.client.HttpClientErrorException;

public class UpdateUser extends Item {


    public UpdateUser(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Update user info";
    }

    @Override
    public void perform() {
        try {
            UpdateUserById.printResponce();
        }catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}