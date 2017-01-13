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

    public static void accountSerialization(CustomArrayList<Account> listOfAccounts) throws IOException {
        try(ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("account.ser"))){
            for (int i = 0; i < listOfAccounts.size(); i++) {
                objectOutputStream.writeObject(listOfAccounts.get(i));
            }
        }
    }

    public static void clientSerialization(List<Client> clients) throws IOException {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream("client.ser"))) {
            for (int i = 0; i < clients.size(); i++) {
                objectOutputStream.writeObject(clients.get(i));
            }
        }
    }
}
