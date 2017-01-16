package by.saidanov.bank.utility.factory;

import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.account.Deposit;
import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.database.DatabaseHelper;
import by.saidanov.bank.utility.Validator;
import by.saidanov.bank.utility.io.AccountIO;
import by.saidanov.bank.utility.io.ClientIO;

import java.io.IOException;

/**
 * AccountFactory
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * AccountFactory creates accounts.
 */
public final class AccountFactory{

    /**
     * <p>Method for creating an Account</p>
     * <p>AccountFactory creating an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @return new Account
     */
    public static Account createAccount(int clientId, int initialContribution,
                                        int accountId) {
        if (Database.listOfAccounts.size() != 0){
            accountId = Database.listOfAccounts.size();
        }
        try {
            accountId = Validator.accountInFileValidation(accountId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account account = new Account(clientId,initialContribution,accountId);
        DatabaseHelper.addToDatabase(clientId, accountId);
        addToFile(clientId, account);
        Account.accountIdCounter++;
        Database.listOfAccounts.add(account);
        return account;
    }

    /**
     * <p>Method for creating a Deposit</p>
     * <p>AccountFactory creating an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @param term the amount of months which the deposit will be kept in the bank
     * @param persentage amount of interest that the client will receive per months
     * @return new Deposit
     */
    public static Account createAccount(int clientId, int initialContribution, int term, double persentage,
                                        DepositCurrency usd, int accountId){
        if (Database.listOfAccounts.size() != 0){
            accountId = Database.listOfAccounts.size();
        }
        try {
            accountId = Validator.accountInFileValidation(accountId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account account = new Deposit(clientId, initialContribution, term, persentage,usd,accountId);
        DatabaseHelper.addToDatabase(clientId, accountId);
        addToFile(clientId, account);
        Account.accountIdCounter++;
        Database.listOfAccounts.add(account);
        return account;
    }

    /**
     * This method adds account to account file, and changes client's list of accounts in client file
     * @param clientId unique id of Client
     * @param account this account will be added to file
     */
    private static void addToFile(int clientId, Account account) {
        try {
            AccountIO accountIO = new AccountIO();
            accountIO.addToFile(account);
            ClientIO clientIO = new ClientIO();
            clientIO.addAccountToClientFile(clientId, account.getAccountId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
