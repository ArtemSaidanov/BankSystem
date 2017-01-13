package by.saidanov.bank.utility.serialization;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.beans.database.DatabaseHelper;

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
        try {
            try (FileInputStream fis = new FileInputStream("account.ser");
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

    public static void clientDesirialization() throws IOException, ClassNotFoundException {
        try {
            try (FileInputStream fis = new FileInputStream("client.ser");
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

    public static String findSerFiles(){
        FileInputStream clientSer = null;
        FileInputStream accountSer = null;
        try {
            clientSer = new FileInputStream("client.ser");
            accountSer = new FileInputStream("account.ser");
            return "Yes";
        } catch (FileNotFoundException e) {
            return "No";
        }finally {
            try {
                clientSer.close();
                accountSer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (NullPointerException e){}
        }
    }

}
