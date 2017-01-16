package by.saidanov.bank.beans.account;

import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.utility.Validator;
import by.saidanov.bank.exceptions.WrongAccountIdException;

import java.io.Serializable;

/**
 * Account
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * The base class for all accounts
 */
public class Account implements Comparable<Account>, Serializable{

    /**This counter ensures the uniqueness of each Account*/
    public static int accountIdCounter = 0;

    public static final long serialVersionUID = 10;

    protected int accountId;

    private int clientId;

    private int initialContribution;

    /**The amount of money in the account.
     *at the time of the creation an account amountOfMoney == initial contribution*/
    protected int amountOfMoney;

    public Account() {
    }

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

    /**
     * @param accountId unique Account id
     * @return account by unique id
     */
    public static Account getAccountById(int accountId) {
        try {
            Validator.accountIDValidation(accountId);
        } catch (WrongAccountIdException e) {
            e.printStackTrace();
        }
        return Database.listOfAccounts.get(accountId);
    }

    @Override
    public String toString() {
        return "Account : " + accountId
                + "; clientId " + clientId
                + "; initialContribution : " + initialContribution
                + "; amountOfMoney : " + amountOfMoney;
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

    @Override
    public int compareTo(Account o) {
        if (amountOfMoney < o.getAmountOfMoney()){
            return -1;
        }else if (amountOfMoney > o.getAmountOfMoney()){
            return 1;
        }else return 0;
    }
}
