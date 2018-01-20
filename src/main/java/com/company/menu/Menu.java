package com.company.menu;
 
import java.util.ArrayList;

public class Menu {
 
    private ArrayList<Item> items;
    private InputOutput inputOutput;
 
    public Menu(ArrayList<Item> items, InputOutput inputOutput) {
        this.items = items;
        this.inputOutput = inputOutput;
    }
 
    public void runMenu() {
        int size = items.size();
        String errMsg = "";
        while (true) {
            System.out.println("\n-----\n");
            if (!errMsg.equals("")) {
                inputOutput.put(errMsg);
            }
            for (int i = 1; i <= size; i++) {
                inputOutput.put(i + ". " + items.get(i - 1).displayedName());
            }
            System.out.println("\n-----\n");

            int itemNumber;
 
            try {
                errMsg = "";
                itemNumber = inputOutput.getInteger("\nEnter item number...");
                items.get(itemNumber - 1).perform();
                if (items.get(itemNumber - 1).isExit()) {
                    break;
                }
            } catch (Exception e) {
                errMsg = "\nItem number is wrong.\n";
            }
        }
 
    }
 
}