package by.saidanov.bank.utility.menu;

import by.saidanov.bank.utility.serialization.Deserialization;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Godfrid on 13.01.2017.
 */
public class CustomMenu {

    public void start(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        greeting();
        try {
            deserialisation(reader);
            System.out.println("Please enter \"help\" to get all base commands");
            MenuHelper.workInApp(reader);
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}

    }

    private void greeting(){
        System.out.println("Hi, you are in the bank app");
    }

    private void deserialisation(BufferedReader reader) throws IOException, ClassNotFoundException{
        if (Deserialization.findSerFiles().equals("Yes")){
            System.out.println("You may deserialize objects from files. Enter \"Y\" to accept or enter \"N\" to skip");
            String reply = reader.readLine();
                if (reply.equals("Y")){
                    Deserialization.clientDesirialization();
                    Deserialization.accountDeserialization();
                }else if (reply.equals("N")){
                    System.out.println("Deserialization ignored");
                }
        }else {
            System.out.println("Deserialization not available. You have to create and serialize your objects to deserialize it.");
        }
    }
}
