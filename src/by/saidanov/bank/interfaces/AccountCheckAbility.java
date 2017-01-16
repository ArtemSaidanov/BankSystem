package by.saidanov.bank.interfaces;

import by.saidanov.bank.beans.account.Account;

import java.util.TreeSet;

/**
 * AccountCheckAbility
 *
 * @version 1.0
 *
 * Date 15.01.2017
 */
public interface AccountCheckAbility {

    /**
     * This method sorts all clients Accounts by amount of money
     * @return TreeSet with accounts*/
    TreeSet<Account> sortAccounts(int clientId);

    /**
     * @param accountId unique accountId
     * @return returns you amount of money that Client has on his account
     */
    int accountBalanceCheck(int accountId);

    /**@return returns you amount of money that Client has on all his accounts*/
    int allAccountsBalanceCheck(int clientId);
}
