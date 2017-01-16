package by.saidanov.bank.utility.serialization;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.utility.Constants;
import by.saidanov.bank.utility.CustomArrayList;
import by.saidanov.bank.utility.io.FileCleaner;

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
public final class Serialization {

    public static void accountSerialization(List<Account> listOfAccounts) throws IOException {
        FileCleaner.cleanFile(Constants.ACCOUNT_SER_FILE);
        try(ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("account.ser"))){
            for (Account listOfAccount : listOfAccounts) {
                objectOutputStream.writeObject(listOfAccount);
            }
        }
    }

    public static void clientSerialization(List<Client> clients) throws IOException {
        FileCleaner.cleanFile(Constants.CLIENT_SER_FILE);
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream("client.ser"))) {
            for (Client client : clients) {
                objectOutputStream.writeObject(client);
            }
        }
    }

}
