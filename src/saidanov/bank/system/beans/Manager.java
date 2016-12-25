package saidanov.bank.system.beans;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.database.DatabaseHelper;
import saidanov.bank.system.beans.factory.AccountFactory;
import saidanov.bank.system.beans.tools.DepositCurrency;

/**
 * Manager helps Client to manage all Clients accounts.
 */
public class Manager {

    private int managerId;

    public Manager(int managerId) {
        this.managerId = managerId;
    }

    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    /**
     * <p>Method for creating an Account</p>
     * <p>Manager calls AccountFactory to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * */
    public static Account createAccount(int clientId, int initialContribution) {
        Account account = AccountFactory.createAccount(clientId, initialContribution, Account.accountIdCounter);
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
    public static Account createAccount(int clientId, int initialContribution, int term, double persentage, DepositCurrency currency) {
        Account account = AccountFactory.createAccount(clientId, initialContribution, term, persentage, currency, Account.accountIdCounter);
        DatabaseHelper.addToDatabase(clientId);
        Account.accountIdCounter++;
        return account;
    }
}
