package saidanov.bank.system.domain.client;

import saidanov.bank.system.beans.interfaces.CreateAccount;
import saidanov.bank.system.domain.Manager;
import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.database.Database;
import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.exceptions.NotEnoughMoneyException;


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
public abstract class Client implements CreateAccount{

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

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * This method returns you a Client object by clientId*/
    public static Client getClientById(int clientId){
      return Database.listOfClients.get(clientId);
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
    public void takeMoneyFromAccount(int accountId, int money) {
        Account account = Database.listOfAccounts.get(accountId);
        account.setAmountOfMoney(account.getAmountOfMoney() - money);
        if(account.getAmountOfMoney()<0){
            account.setAmountOfMoney(account.getAmountOfMoney() + money);
            try {
                throw new NotEnoughMoneyException();
            } catch (NotEnoughMoneyException e) {
                System.out.println("Sorry, you do not have enough money." +
                        "Your amount of money in this account: " +  account.getAmountOfMoney());
            }
        }
    }

    /**
     * This method puts money on account
     * @param accountId unique accountId
     * @param money money that Client decided to take*/
    public void putMoneyToAccount(int accountId, int money){
        //TODO
    }

    /**This method is not ready yet*/
    public void deleteAccount(int clientId, int accountId) {
        //TODO
    }


}

