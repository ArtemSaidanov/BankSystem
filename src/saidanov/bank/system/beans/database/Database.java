package saidanov.bank.system.beans.database;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.client.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Database
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * This database contains listOfClients and clientsAndAccount
 */
public final class Database {

    /**
     * This list contains all Client*/
    public static List<Client> listOfClients = new ArrayList<>();

    /**
     * This list contains all Accounts*/
    public static List<Account> listOfAccounts = new ArrayList<>();

    /**
     * This Map contains ClientId and list of his accounts ids*/
    public static Map<Integer, List<Integer>> clientsAndAccounts = new HashMap<>();

}
