package saidanov.bank.system;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.client.Client;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.beans.factory.ClientFactory;

import java.util.List;


/**
 * The main class.
 */
public class BankSystemRunner {

    public static void main(String[] args) {
        //TODO
        Client client = ClientFactory.createClient("Andrew", "Brown", 22);
        Client client1 = ClientFactory.createClient("Photography", "Art.Saidanov");

        client.createAccount(client.getClientId(),2150);
        client1.createAccount(client1.getClientId(),350);

        List<Integer> list = Database.clientsAndAccounts.get(client.getClientId());
        int i = list.get(0);
        Account account = Account.getAccountById(i);
        account.getAmountOfMoney();

        System.out.println(Account.getAccountById(
                Database.clientsAndAccounts.get(
                        client.getClientId()).get(0)).getAmountOfMoney());
        client.takeMoneyFromAccount(0,20);
        client1.takeMoneyFromAccount(1,30);
    }
}
