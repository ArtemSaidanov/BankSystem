package saidanov.bank.system.beans.database;

import saidanov.bank.system.beans.account.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Godfrid on 26.12.2016.
 */
public final class DatabaseHelper {

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
        int i = list.get(0);
    }
}
