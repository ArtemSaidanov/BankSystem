package by.saidanov.bank.utility.serialization;

import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.account.Deposit;
import by.saidanov.bank.utility.Constants;
import by.saidanov.bank.utility.CustomArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * SerializationHelper
 *
 * @version 1.0
 *
 * Date 16.01.2017
 *
 * This class encapsulates method that returns a list with accounts
 */
public class SerializationHelper {

    public static List<Account> getAccountList(){
        List<Account> listOfAccounts = new CustomArrayList<>();
        Account account;
        int clientId;
        int amountOfMoney;
        int accountID;
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.ACCOUNT_FILE_PATH))) {
            while (reader.ready()){

            String accountStr = reader.readLine();
            String[] splitedAccountStr = accountStr.split(" ");

            if (splitedAccountStr.length == Constants.ACCOUNT_LENGTH_IN_FILE){
                clientId = Integer.parseInt(splitedAccountStr[Constants.CLIENT_ID_IN_ACCOUNT_FILE]);
                amountOfMoney = Integer.parseInt(splitedAccountStr[Constants.AMOUNT_OF_MONEY_IN_FILE]);
                accountID = Integer.parseInt(splitedAccountStr[Constants.ACCOUNT_IN_FILE_ID]);

                account = new Account(clientId, amountOfMoney, accountID);
                listOfAccounts.add(account);

            }else if (splitedAccountStr.length == Constants.DEPOSIT_LENGTH_IN_FILE){
                clientId = Integer.parseInt(splitedAccountStr[Constants.CLIENT_ID_IN_ACCOUNT_FILE]);
                amountOfMoney = Integer.parseInt(splitedAccountStr[Constants.AMOUNT_OF_MONEY_IN_FILE]);
                int term = Integer.parseInt(splitedAccountStr[Constants.TERM_IN_FILE]);
                double persentage = Double.parseDouble(splitedAccountStr[Constants.PERSENTAGE_IN_FILE]);
                DepositCurrency depositCurrency = getDepositCurrency(splitedAccountStr[Constants.DEPOSIT_CURRENCY_IN_FILE]);
                accountID = Integer.parseInt(splitedAccountStr[Constants.ACCOUNT_IN_FILE_ID]);

                account = new Deposit(clientId,amountOfMoney,term,persentage,depositCurrency,accountID);
                Deposit deposit = (Deposit) account;
                deposit.setDepositProfitInInt(Integer.parseInt(splitedAccountStr[Constants.DEPOSIT_PROFIT_IN_FILE]));
                listOfAccounts.add(deposit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfAccounts;
    }

    private static DepositCurrency getDepositCurrency(String s) {
        DepositCurrency depositCurrency = null;
        switch (s) {
            case "USD":
                depositCurrency = DepositCurrency.USD;
                break;
            case "EUR":
                depositCurrency = DepositCurrency.EUR;
                break;
            case "RUB":
                depositCurrency = DepositCurrency.RUB;
                break;
        }
        return depositCurrency;
    }
}
