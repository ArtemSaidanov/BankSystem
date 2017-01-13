package by.saidanov.bank;


import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.utility.factory.ClientFactory;
import by.saidanov.bank.utility.menu.CustomMenu;
import by.saidanov.bank.utility.serialization.Serialization;

import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * The main class.
 */
public class BankRunner {

    public static void main(String[] args) {
        //TODO Menu, JavaDoc, Schema
//        Client client = ClientFactory.createClient("Andrew", "Boss", 25, new GregorianCalendar());
//        client.createAccount(204);
//        Client client1 = ClientFactory.createClient("Photography", "Dan", new GregorianCalendar());
//        client1.createAccount(1000, 10, 10, DepositCurrency.RUB);
//        try {
//            Serialization.clientSerialization(Database.listOfClients);
//            Serialization.accountSerialization(Database.listOfAccounts);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        CustomMenu menu = new CustomMenu();
        menu.start();
    }
}
