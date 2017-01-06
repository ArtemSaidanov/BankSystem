package saidanov.bank.system.domain;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.account.Deposit;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.beans.database.DatabaseHelper;
import saidanov.bank.system.beans.fileworking.AccountFile;
import saidanov.bank.system.beans.interfaces.AccountChangeAbility;
import saidanov.bank.system.domain.factory.AccountFactory;
import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.exceptions.NotEnoughMoneyException;
import saidanov.bank.system.exceptions.TermCanNotRaiseException;

import java.io.IOException;

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
     * */
    public Account createAccount(int clientId, int initialContribution) {
        Account account = new AccountFactory().createAccount(clientId, initialContribution, Account.accountIdCounter);
        DatabaseHelper.addToDatabase(clientId);
        try {
            AccountFile accountFile = new AccountFile();
            accountFile.addToFile(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            AccountFile accountFile = new AccountFile();
            accountFile.addToFile(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account.accountIdCounter++;
        return account;
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
        try {
            AccountFile accountFile = new AccountFile();
            accountFile.changeAmountOfMoney(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method puts money into the account
     * @param accountId unique accountId
     * @param money money that Client decided to take*/
    public void putMoney(int accountId, int money) {
        Account account = (Account) Database.listOfAccounts.get(accountId);
        account.setAmountOfMoney(account.getAmountOfMoney() + money);
    }

    /**This method deletes Client's account*/
    public void deleteAccount(int accountId) throws IOException {
        new AccountFile().deleteAccount(accountId);
        Account account = Account.getAccountById(accountId);
        account.setAccountId(-1);
    }

    public void setTerm(Account account, int pastTerm) throws IOException, TermCanNotRaiseException {
        Deposit deposit = (Deposit) account;
        deposit.setTerm(pastTerm);
    }
}
