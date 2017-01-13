package by.saidanov.bank;

import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.utility.database.Database;
import by.saidanov.bank.utility.factory.ClientFactory;
import by.saidanov.bank.utility.serialization.Deserialization;
import by.saidanov.bank.utility.serialization.Serialization;

import java.io.*;


/**
 * The main class.
 */
public class BankRunner {

    public static void main(String[] args) {

        //TODO Menu, GregorianCalendar, JavaDoc, Schema
        Client client = ClientFactory.createClient("Andrew", "Brown", 22);
        Client client1 = ClientFactory.createClient("paul", "diaaaa", 42);
        Client client2 = ClientFactory.createClient("dodo", "huilo", 235);
        Client client3 = ClientFactory.createClient("dildo", "shmalens", 12);
        client.createAccount(200);
        client1.createAccount(1234415);
        client1.createAccount(1234415);
        client2.createAccount(45455);
        client2.createAccount(45455);
        client3.createAccount(787848);
        for (int i = 0; i < Database.listOfAccounts.size(); i++) {
            Account account = (Account) Database.listOfAccounts.get(i);
            System.out.println(account.getAccountId() + "  ---  " + account.getClientId());

        }

//        client.createAccount(Account.accountIdCounter, 1000, 12, 15.0, DepositCurrency.EUR);
//        client.createAccount(Account.accountIdCounter, 1000, 12, 15.0, DepositCurrency.EUR);
//        for (int i = 0; i < Database.listOfAccounts.size(); i++) {
//            Account account = (Account) Database.listOfAccounts.get(i);
//            System.out.println(account.getClientId());
//
//        }
//        client.createAccount(Account.accountIdCounter, 1000, 12, 15.0, DepositCurrency.EUR);
//        client.createAccount(Account.accountIdCounter, 1000, 12, 15.0, DepositCurrency.EUR);
//        client.createAccount(Account.accountIdCounter, 1000, 12, 15.0, DepositCurrency.EUR);
//        try {
//            Serialization.clientSerialization(Database.listOfClients);
//            Serialization.accountSerialization(Database.listOfAccounts);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            Deserialization.clientDesirialization();
//            Deserialization.accountDeserialization();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}
