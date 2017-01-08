package saidanov.bank.system.beans.account;

import saidanov.bank.system.beans.Validator;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.exceptions.WrongAccountIdException;

/**
 * Account
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * The base class for all accounts
 */
public class Account {

    /**This counter ensures the uniqueness of each Account*/
    public static int accountIdCounter = 0;

    protected int accountId;

    private int clientId;

    private int initialContribution;

    /**amountOfMoney will raise if Client decided to put money into the Account, and will fall if Client decided to take money from account.
     * At the time of account creation amountOfMoney == initial contribution*/
    protected int amountOfMoney;

    /**
     * Constructor of Account
     * @param accountId unique Account id
     * @param clientId unique Client id
     * @param initialContribution is the amount of money that Client put into the account at first time
     */
    public Account(int clientId, int initialContribution, int accountId) {
        this.accountId = accountId;
        this.initialContribution = initialContribution;
        this.amountOfMoney = initialContribution;
        this.clientId = clientId;
    }

    public static Account getAccountById(int accountId) {
        try {
            Validator.accountIDValidation(accountId);
        } catch (WrongAccountIdException e) {
            e.printStackTrace();
        }
        return (Account) Database.listOfAccounts.get(accountId);
    }

    @Override
    public String toString() {
        return "Account :" + accountId
                + "clientId " + clientId
                + "initialContribution :" + initialContribution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (clientId != account.clientId) return false;
        if (initialContribution != account.initialContribution) return false;
        return amountOfMoney == account.amountOfMoney;

    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + clientId;
        result = 31 * result + initialContribution;
        result = 31 * result + amountOfMoney;
        return result;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public int setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
        return amountOfMoney;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getInitialContribution() {
        return initialContribution;
    }
}
