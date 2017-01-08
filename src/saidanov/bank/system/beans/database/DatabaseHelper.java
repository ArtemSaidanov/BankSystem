package saidanov.bank.system.beans.database;

import saidanov.bank.system.beans.account.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHelper
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>This class managing Database</p>
 */
public final class DatabaseHelper {

    /**
     * This method adds unique clientId and list of client account ids
     * to the Map in database*
     */
    public static void addToDatabase(int clientId){
        List<Integer> list;
        try {
            list = Database.clientsAndAccounts.get(clientId);
            list.add(Account.accountIdCounter);
        }catch (NullPointerException e){
            list = new ArrayList<>();
            list.add(Account.accountIdCounter);
        }
        Database.clientsAndAccounts.put(clientId, list);
    }
}
