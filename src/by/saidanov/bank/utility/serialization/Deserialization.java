package by.saidanov.bank.utility.serialization;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.beans.database.DatabaseHelper;
import by.saidanov.bank.utility.Constants;

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
public final class Deserialization {

    /**This method deserialize Account objects from file*/
    public static void accountDeserialization() throws IOException, ClassNotFoundException {
        try {
            try (FileInputStream fis = new FileInputStream(Constants.ACCOUNT_SER_FILE);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {
                while(fis.available() != 0) {
                    Account account = (Account) objectInputStream.readObject();
                    System.out.println(account);
                    Database.listOfAccounts.add(account);
                    DatabaseHelper.addToDatabase(account.getClientId(), account.getAccountId());
                }
                System.out.println("Accounts deserialization complete. Call Database to get objects");
            }
        }catch (EOFException eof){
            System.out.println("Deserialization failed with EOFException. Create objects, serialize them and then you will be able to deserialize them.");
        }
    }

    /**This method deserialize Client objects from file*/
    public static void clientDesirialization() throws IOException, ClassNotFoundException {
        try {
            try (FileInputStream fis = new FileInputStream(Constants.CLIENT_SER_FILE);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {
                while (fis.available() != 0) {
                    Client client = (Client) objectInputStream.readObject();
                    System.out.println(client);
                    Database.listOfClients.add(client);
                }
                System.out.println("Clients deserialization complete. Call Database to get objects");
            }
        }catch (EOFException eof){
            System.out.println("Deserialization failed with EOFException. Create objects, serialize them and then you will be able to deserialize them.");
        }
    }

    /**This method tries to find .ser files
     * @return true if files are found
     */
    public static boolean findSerFiles(){
        FileInputStream clientSer = null;
        FileInputStream accountSer = null;
        try {
            clientSer = new FileInputStream(Constants.CLIENT_SER_FILE);
            accountSer = new FileInputStream(Constants.ACCOUNT_SER_FILE);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }finally {
            try {
                if (clientSer != null) {
                    clientSer.close();
                }
                if (accountSer != null) {
                    accountSer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
