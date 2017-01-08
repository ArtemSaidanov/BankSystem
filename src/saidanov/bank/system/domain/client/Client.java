package saidanov.bank.system.domain.client;

import saidanov.bank.system.beans.account.Deposit;
import saidanov.bank.system.beans.interfaces.AccountChangeAbility;
import saidanov.bank.system.domain.Manager;
import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.exceptions.NotEnoughMoneyException;
import saidanov.bank.system.exceptions.TermCanNotRaiseException;

import java.io.IOException;
import java.util.List;


/**
 * Client
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>This class is the base class of Individual and LegalEntity.</p>
 * <p>Client communicates with class Manager.</p>
 * <p>Client has basic methods to manage accounts</p>
 */
public abstract class Client implements AccountChangeAbility {

    /**This counter ensures the uniqueness of each Client*/
    public static int clientIdCounter = 0;
    private int clientId;

    Client(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return clientId == client.clientId;

    }

    @Override
    public int hashCode() {
        return clientId;
    }

    public int getClientId() {
        return clientId;
    }

    /**
     * This method returns you a Client object by clientId
     */
    public static Client getClientById(int clientId){
      return Database.listOfClients.get(clientId);
    }

    /**
     * @return returns you all acountIds that Client has
     */
    private List<Integer> getAccountList(int clientId){
        return Database.clientsAndAccounts.get(clientId);
    }

    /**
     * <p>Method for creating an Account</p>
     * <p>Client calls Manager to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     */
    public Account createAccount(int clientId,int initialContribution) {
        Manager manager = new Manager();
        return manager.createAccount(clientId,initialContribution);
    }

    /**
     * <p>Method for creating a Deposit</p>
     * <p>Client calls Manager to create an account</p>
     * @param clientId unique id of Client
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @param term the amount of months which the deposit will be kept in the bank
     * @param persentage amount of interest that the client will receive per month
     */
    public Account createAccount(int clientId, int initialContribution, int term,
                                 double persentage, DepositCurrency currency) {
        Manager manager = new Manager();
        return manager.createAccount(clientId, initialContribution, term,
                persentage, currency);
    }


    /**
     * <p>Method allows the Client to take money from his account</p>
     * @param accountId unique accountId
     * @param money money that Client decided to take
     */
    public void takeMoney(int accountId, int money) throws NotEnoughMoneyException {
      new Manager().takeMoney(accountId,money);
    }

    /**
     * This method puts money into the account
     * @param accountId unique accountId
     * @param money money that Client decided to take*/
    public void putMoney(int accountId, int money) {
        new Manager().putMoney(accountId, money);
    }

    /**
     * @param accountId unique accountId
     * @return returns you amount of money that Client has on his account
     */
    public int accountBalanceCheck(int accountId){
        return Account.getAccountById(accountId).getAmountOfMoney();
    }

    /**
     * @param clientId unique clientId
     * @return returns you amount of money that Client has on all his accounts
     */
    public int allAccountsBalanceCheck(int clientId){
        List<Integer> list = getAccountList(clientId);
        /**This int contains summ of Client's money from all Accounts*/
        int allMoney = 0;
        for (Integer aList : list) {
            Account account = Account.getAccountById(aList);
            allMoney += account.getAmountOfMoney();
        }
        return allMoney;
    }

    /**
     * This method deletes account by ID
     */
    public void deleteAccount(int accountId) throws IOException{
        new Manager().deleteAccount(accountId);
    }

    /**This method allows Client to set term of his deposit*/
    public void setTerm(Account account, int pastTerm) throws IOException, TermCanNotRaiseException{
        if (! (account instanceof Deposit)){
            throw new IllegalArgumentException("This Account is not Deposit and do not have term");
        }else new Manager().setTerm(account, pastTerm);
    }
}

