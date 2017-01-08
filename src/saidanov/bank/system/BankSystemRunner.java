package saidanov.bank.system;

import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.account.Deposit;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.domain.client.Client;
import saidanov.bank.system.domain.factory.ClientFactory;
import saidanov.bank.system.exceptions.NotEnoughMoneyException;
import saidanov.bank.system.exceptions.TermCanNotRaiseException;

import java.io.IOException;


/**
 * The main class.
 */
public class BankSystemRunner {

    public static void main(String[] args) {
        //TODO Comparable or Comparator(client's accounts sort), Serialazeable
        Client client = ClientFactory.createClient("Andrew", "Brown", 22);
//        Client client1 = ClientFactory.createClient("SuperBusiness", "BigRussianBoss");
        client.createAccount(client.getClientId(),250);
        client.createAccount(client.getClientId(),250);
        client.createAccount(client.getClientId(),250);
        try {
            client.deleteAccount(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        client.createAccount(client.getClientId(),1000,10,10.0,DepositCurrency.USD);
//        client.createAccount(client.getClientId(),150);
        System.out.println(Database.clientsAndAccounts.get(client.getClientId()));
//        try {
//            Thread.sleep(1000);
//
//            client.takeMoney(0,100);
//
//            Thread.sleep(1000);
//
//            client.setTerm(Account.getAccountById(1), 4);
//
//            Deposit deposit = (Deposit) Account.getAccountById(1);
//            System.out.println(Account.getAccountById(1).getAmountOfMoney() + " " + deposit.getTerm() + " "+  deposit.getDepositProfit());
//
//            Thread.sleep(1000);
//
//            client.takeMoney(1, 500);
//            System.out.println(Account.getAccountById(1).getAmountOfMoney() + " " + deposit.getTerm() + " " + deposit.getDepositProfit());
//
//            Thread.sleep(1000);
//
//            client.deleteAccount(1);
//        } catch (InterruptedException | TermCanNotRaiseException | IOException | NotEnoughMoneyException e) {
//            e.printStackTrace();
//        }

    }
}
