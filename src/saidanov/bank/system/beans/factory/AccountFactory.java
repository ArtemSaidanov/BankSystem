package saidanov.bank.system.beans.factory;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.account.Deposit;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.beans.tools.DepositCurrency;

/**
 * <p>AccountFactory creates accounts.</p>
 * <p>AccountFactory communicates only with Manager.</p>
 */
public final class AccountFactory {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * <p>Method for creating an Account</p>
     * <p>AccountFactory creating an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @return new Account
     * */
    public static Account createAccount(int clientId, int initialContribution, int accountId) {
        Account account = new Account(clientId,initialContribution,accountId);
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
     * */
    public static Account createAccount(int clientId, int initialContribution, int term, double persentage, DepositCurrency usd, int accountId){
        Account account = new Deposit(clientId,initialContribution,term,persentage,usd,accountId);
        Database.listOfAccounts.add(account);
        return account;
    }
}
