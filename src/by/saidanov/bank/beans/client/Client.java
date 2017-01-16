package by.saidanov.bank.beans.client;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.account.Deposit;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.interfaces.AccountChangeAbility;
import by.saidanov.bank.exceptions.NotEnoughMoneyException;
import by.saidanov.bank.beans.Manager;
import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.exceptions.TermCanNotRaiseException;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;


/**
 * Client
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>This class is the base class of Individual and LegalEntity.</p>
 * <p>Client communicates with class Manager.</p>
 */
public abstract class Client implements AccountChangeAbility, Serializable {

    /**This counter ensures the uniqueness of each Client*/
    public static int clientIdCounter = 0;

    public static final long serialVersionUID = 1;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd.MM.yyyy");

    private String dateOfRegistration;

    private int clientId;

    public Client() {
    }

    /**
     * @param clientId unique Client id
     * @param gregorianCalendar used for obtaining dateOfRegistration
     */
    public Client(int clientId, GregorianCalendar gregorianCalendar) {
        this.clientId = clientId;
        this.dateOfRegistration = sdf.format(gregorianCalendar.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;
        return dateOfRegistration != null ? dateOfRegistration.equals(client.dateOfRegistration) : client.dateOfRegistration == null;

    }

    @Override
    public int hashCode() {
        int result = dateOfRegistration != null ? dateOfRegistration.hashCode() : 0;
        result = 31 * result + clientId;
        return result;
    }

    /**
     * This method returns you a Client object by clientId
     * @param clientId unique Client id
     */
    public static Client getClientById(int clientId){
      return Database.listOfClients.get(clientId);
    }

    /**
     * @return returns you all accountIds of accounts that Client has
     */
    public static List<Integer> getAccountList(int clientId){
        return Database.clientsAndAccounts.get(clientId);
    }

    /**
     * <p>Method for creating an Account</p>
     * <p>Client calls Manager to create an account</p>
     * @param initialContribution is the amount of money that Client put into the account at first time
     */
    public Account createAccount(int initialContribution) {
        Manager manager = new Manager();
        return manager.createAccount(this.clientId,initialContribution);
    }

    /**
     * <p>Method for creating a Deposit</p>
     * <p>Client calls Manager to create a deposit</p>
     * @param initialContribution is the amount of money that Client put into the account at first time
     * @param term deposit duration(sets in month)
     * @param persentage amount of interest that the client will receive per month
     */
    public Account createAccount(int initialContribution, int term,
                                 double persentage, DepositCurrency currency) {
        Manager manager = new Manager();
        return manager.createAccount(this.clientId, initialContribution, term,
                persentage, currency);
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public int getClientId() {
        return clientId;
    }

    public void takeMoney(int accountId, int money) throws NotEnoughMoneyException {
      new Manager().takeMoney(accountId,money);
    }

    public void putMoney(int accountId, int money) {
        new Manager().putMoney(accountId, money);
    }

    public void deleteAccount(int accountId) throws IOException{
        new Manager().deleteAccount(accountId);
    }

    public void setTerm(Account account, int pastTerm) throws IOException, TermCanNotRaiseException{
        if (! (account instanceof Deposit)){
            throw new IllegalArgumentException("This Account is not Deposit and do not have term");
        }else new Manager().setTerm(account, pastTerm);
    }

    public TreeSet<Account> sortAccounts(){
        return new Manager().sortAccounts(clientId);
    }

    public int accountBalanceCheck(int accountId){
        return new Manager().accountBalanceCheck(accountId);
    }

    public int allAccountsBalanceCheck(){
        return new Manager().allAccountsBalanceCheck(clientId);
    }
}

