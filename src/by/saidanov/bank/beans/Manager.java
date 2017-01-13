package by.saidanov.bank.beans;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.account.Deposit;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.beans.database.DatabaseHelper;
import by.saidanov.bank.beans.interfaces.AccountChangeAbility;
import by.saidanov.bank.utility.io.AccountIO;
import by.saidanov.bank.utility.io.ClientIO;
import by.saidanov.bank.utility.factory.AccountFactory;
import by.saidanov.bank.exceptions.NotEnoughMoneyException;
import by.saidanov.bank.exceptions.TermCanNotRaiseException;

import java.io.IOException;
import java.util.List;

/**
 * Manager
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * Manager helps Client to manage all Clients accounts.
 */
public class Manager implements AccountChangeAbility {

    public Manager() {
    }

    /**
     * <p>Method for creating an Account</p>
     * <p>Manager calls AccountFactory to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     */
    public Account createAccount(int clientId, int initialContribution) {
        return AccountFactory.createAccount(clientId,initialContribution,Account.accountIdCounter);
    }

    /**
     * <p>Method for creating a Deposit</p>
     * <p>Manager calls AccountFactory to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @param term the amount of months which the deposit will be kept in the bank
     * @param persentage amount of interest that the client will receive per months
     */
    public Account createAccount(int clientId, int initialContribution, int term, double persentage, DepositCurrency currency) {
        return AccountFactory.createAccount(clientId, initialContribution, term, persentage, currency, Account.accountIdCounter);
    }

    /**
     * <p>Method allows the Client to take money from his account</p>
     * @param accountId unique accountId
     * @param money money that Client decided to take
     */
    public void takeMoney(int accountId, int money) throws NotEnoughMoneyException {
        Account account = (Account) Database.listOfAccounts.get(accountId);
        int i = account.setAmountOfMoney(account.getAmountOfMoney() - money);
        if(i<0){
            account.setAmountOfMoney(account.getAmountOfMoney() + money);
            throw new NotEnoughMoneyException("Sorry, you do not have enough money." +
                    "Your amount of money in this account: " +  account.getAmountOfMoney());
        }
        account.setAmountOfMoney(i);
        Database.listOfAccounts.set(accountId, account);
        try {
            AccountIO accountIO = new AccountIO();
            accountIO.changeAmountOfMoney(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method puts money into the account
     * @param accountId unique accountId
     * @param money money that Client decided to take
     */
    public void putMoney(int accountId, int money) {
        Account account = (Account) Database.listOfAccounts.get(accountId);
        account.setAmountOfMoney(account.getAmountOfMoney() + money);
    }

    /**This method deletes Client's account
     */
    public void deleteAccount(int accountId) throws IOException {
        int clientID = Account.getAccountById(accountId).getClientId();
        new ClientIO().deleteAccountFromClientFile(clientID, accountId);
        new AccountIO().deleteAccount(accountId);
        List<Integer> listOfAccounts = Database.clientsAndAccounts.get(clientID);
        for (Integer i : listOfAccounts) {
            if (i == accountId){
                listOfAccounts.remove(i);
            }
        }
        Database.clientsAndAccounts.put(clientID,listOfAccounts);
        Account account = Account.getAccountById(accountId);
        account.setAccountId(-1);
    }

    /**
     * This method sets term of deposit
     */
    public void setTerm(Account account, int pastTerm) throws IOException, TermCanNotRaiseException {
        Deposit deposit = (Deposit) account;
        deposit.setTerm(pastTerm);
    }
}
