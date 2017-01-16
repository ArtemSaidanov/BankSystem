package by.saidanov.bank.utility.menu;

import by.saidanov.bank.utility.Constants;
import by.saidanov.bank.utility.io.FileCleaner;
import by.saidanov.bank.utility.serialization.Deserialization;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CustomMenu
 *
 * @version 1.0
 *
 * Date 13.01.2017
 */
public class CustomMenu {

    /**This method starts menu*/
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

    /**
     * This method offers to deserialize objects when program starts
     * @param reader BufferedReader to read user answer from console*/
    private void deserialisation(BufferedReader reader) throws IOException, ClassNotFoundException{
        if (Deserialization.findSerFiles()){
            System.out.println("You may deserialize objects from files. Enter \"Y\" to accept or enter \"N\" to skip");
            String reply = reader.readLine();
                if (reply.equals("Y")){
                    Deserialization.clientDesirialization();
                    Deserialization.accountDeserialization();
                }else if (reply.equals("N")){
                    FileCleaner.cleanFile(Constants.ACCOUNT_FILE_PATH);
                    FileCleaner.cleanFile(Constants.CLIENT_FILE_PATH);
                    System.out.println("Deserialization ignored");
                }
        }else {
            System.out.println("Deserialization not available. You have to create and serialize your objects to deserialize it.");
        }
    }
}
