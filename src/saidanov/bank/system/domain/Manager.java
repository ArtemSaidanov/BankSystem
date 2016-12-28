package saidanov.bank.system.domain;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.database.DatabaseHelper;
import saidanov.bank.system.beans.interfaces.CreateAccount;
import saidanov.bank.system.domain.factory.AccountFactory;
import saidanov.bank.system.beans.DepositCurrency;

/**
 * Manager
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * Manager helps Client to manage all Clients accounts.
 */
public class Manager implements CreateAccount {

    public Manager() {
    }

    /**
     * <p>Method for creating an Account</p>
     * <p>Manager calls AccountFactory to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * */
    public Account createAccount(int clientId, int initialContribution) {
        Account account = new AccountFactory().createAccount(clientId, initialContribution, Account.accountIdCounter);
        DatabaseHelper.addToDatabase(clientId);
        Account.accountIdCounter++;
        return account;
    }

    /**
     * <p>Method for creating a Deposit</p>
     * <p>Manager calls AccountFactory to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @param term the amount of months which the deposit will be kept in the bank
     * @param persentage amount of interest that the client will receive per months
     * */
    public Account createAccount(int clientId, int initialContribution, int term, double persentage, DepositCurrency currency) {
        Account account = new AccountFactory().createAccount(clientId, initialContribution, term, persentage, currency, Account.accountIdCounter);
        DatabaseHelper.addToDatabase(clientId);
        Account.accountIdCounter++;
        return account;
    }
}
