package by.saidanov.bank.utility.serialization;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.utility.CustomArrayList;

import java.io.*;
import java.util.List;

/**
 * Serialization
 *
 * @version 1.0
 *
 * Date 12.01.2017
 *
 * This class serialize clients and accounts
 */
public class Serialization {

    private final static String ACCOUNT_SER_FILE_PATH = "account.ser";
    private final static String CLIENT_SER_FILE_PATH = "client.ser";

    public static void accountSerialization(CustomArrayList<Account> listOfAccounts) throws IOException {
        Serialization.serFileCleaner(ACCOUNT_SER_FILE_PATH);
        try(ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("account.ser"))){
            for (int i = 0; i < listOfAccounts.size(); i++) {
                objectOutputStream.writeObject(listOfAccounts.get(i));
            }
        }
    }

    public static void clientSerialization(List<Client> clients) throws IOException {
        Serialization.serFileCleaner(CLIENT_SER_FILE_PATH);
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream("client.ser"))) {
            for (int i = 0; i < clients.size(); i++) {
                objectOutputStream.writeObject(clients.get(i));
            }
        }
    }

    public static void serFileCleaner(String filePath){
        FileWriter fileCleaner = null;
        try {
            fileCleaner = new FileWriter(filePath);
            fileCleaner.write("");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fileCleaner.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }

    }
}
