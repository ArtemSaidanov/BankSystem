package saidanov.bank.system;

import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.account.Deposit;
import saidanov.bank.system.domain.client.Client;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.domain.factory.ClientFactory;
import saidanov.bank.system.exceptions.NotEnoughMoneyException;
import saidanov.bank.system.exceptions.TermCanNotRaiseException;

import java.util.List;


/**
 * The main class.
 */
public class BankSystemRunner {

    public static void main(String[] args) {
        //TODO
        Client client = ClientFactory.createClient("Andrew", "Brown", 22);
        Client client1 = ClientFactory.createClient("Ax", "Dax", 22);
        System.out.println(client.getClientId());

        client.createAccount(client.getClientId(),500, 12, 5, DepositCurrency.USD);
        client.createAccount(client.getClientId(),1000, 12, 5, DepositCurrency.USD);
        client1.createAccount(client1.getClientId(),750, 12, 5, DepositCurrency.USD);
        client.createAccount(client.getClientId(), 600, 12, 5, DepositCurrency.USD);
        client1.createAccount(client1.getClientId(),200, 12, 5, DepositCurrency.USD);
        client.createAccount(client.getClientId(), 100, 12, 5, DepositCurrency.USD);
        System.out.println(client.getClientId());

        List<Integer> list = Database.clientsAndAccounts.get(client.getClientId());
        System.out.println(list);

        System.out.println(client.allAccountsBalanceCheck(client.getClientId()));

        Account account = Account.getAccountById(list.get(0));
        Deposit deposit = (Deposit) account;
        System.out.println(account);

        try {
            deposit.setTerm(11000);
        } catch (TermCanNotRaiseException e) {
            e.printStackTrace();
        }
        System.out.println("Profit after one month  " + deposit.getDepositProfit());

        try {
            client.takeMoney(0, 250);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }

        System.out.println("Profit after takeMoney  " + deposit.getDepositProfit());

//        deposit.setTerm(5);
//        System.out.println(deposit.getDepositProfit());
//        System.out.println(account.getAmountOfMoney());
//
//
//        client.putMoney(0, 251);
//        System.out.println(account.getAmountOfMoney());

    }
}
