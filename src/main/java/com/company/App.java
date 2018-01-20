package com.company;

import com.company.menu.*;
import com.company.menu.items.*;

import java.util.ArrayList;

public class App {

    private static InputOutput inputOutput = new ConsoleInputOutput();

    public static void main(String[] args) {
        ArrayList<Item> items = getItems();
        Menu menu = new Menu(items, inputOutput);
        menu.runMenu();

    }

    private static ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();
        res.add(new Register(inputOutput));
        res.add(new Login(inputOutput));
        res.add(new AllUsers(inputOutput));
        res.add(new GetUser(inputOutput));
        res.add(new UpdateUser(inputOutput));
        res.add(new Logout(inputOutput));
        res.add(new ExitItem(inputOutput));


        return res;
    }

}