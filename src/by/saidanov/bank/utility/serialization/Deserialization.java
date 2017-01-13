package by.saidanov.bank.utility.serialization;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.utility.database.Database;
import by.saidanov.bank.utility.database.DatabaseHelper;

import java.io.*;

/**
 * Deserialization
 *
 * @version 1.0
 *
 * Date 12.01.2017
 *
 * This class deserialize clients and accounts
 */
public class Deserialization {

    public static void accountDeserialization() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("account.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {
            while(fis.available() != 0) {
                Account account = (Account) objectInputStream.readObject();
                System.out.println(account);
                Database.listOfAccounts.add(account);
                DatabaseHelper.addToDatabase(account.getClientId());
            }
        }
    }

    public static void clientDesirialization() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("client.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {
            while (fis.available() != 0){
            Client client = (Client) objectInputStream.readObject();
            System.out.println(client);
            Database.listOfClients.add(client);
            }
        }

    }

}
