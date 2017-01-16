package by.saidanov.bank.utility.io;

import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.account.Deposit;
import by.saidanov.bank.interfaces.AccountFileChangeAbility;
import by.saidanov.bank.utility.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountIO
 *
 * @version 1.0
 *
 * Date 04.01.2017
 *
 * This class works with files like with Database
 * AccountIO includes list of account with all account's parameters
 */
public final class AccountIO implements AccountFileChangeAbility {

    /**This method adds new Account to File*/
	public void addToFile(Account account){
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(Constants.ACCOUNT_FILE_PATH, true);
            if (account instanceof Deposit) { //Deposit creation
                fileWriter.write(account.getAccountId() + " - Account ID" + " Client ID: " + account.getClientId()
                        + " Amount of money: " + account.getAmountOfMoney()
                        + " term: " + ((Deposit) account).getTerm()
                        + " persentage: " + ((Deposit) account).getPersentage()
                        + " deposit profit: " + ((Deposit) account).getDepositProfit()
                        + " currency: " + ((Deposit) account).getCurrency()
                        + "\r" + "\n");
            } else //Account creation
                fileWriter.write(account.getAccountId() + " - Account ID"
                        + " Client ID: " + account.getClientId()
                        + " Amount of money: " + account.getAmountOfMoney()
                        + "\r" + "\n");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	/**
	 * This method changes the account's amount of money in the File
	 * This method calls when Client takes money from the account or puts money into the account
	 */
	public void changeAmountOfMoney(Account account) throws IOException {
		int accountInFileID;
        List<String> listOfLines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.ACCOUNT_FILE_PATH))) {
        /*This "while" reads lines from file*/
            while (fileReader.ready()) {
                String lineFromFile = fileReader.readLine();
                /*Split lineFromFile by spaces*/
                String[] splitLineFromFile = lineFromFile.split(" ");
                try {
                    accountInFileID = Integer.parseInt(splitLineFromFile[Constants.ACCOUNT_IN_FILE_ID]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException in AccountIO");
                    continue;
                }
                int accountToChangeID = account.getAccountId();
                if (accountInFileID == accountToChangeID) {
                    /*Change the value of amountOfMoney*/
                    splitLineFromFile[Constants.AMOUNT_OF_MONEY_IN_FILE] = String.valueOf(account.getAmountOfMoney());
                    if (account instanceof Deposit) {
                        int depositProfit = Integer.parseInt(splitLineFromFile[Constants.DEPOSIT_PROFIT_IN_FILE]) / 2;
                        splitLineFromFile[Constants.DEPOSIT_PROFIT_IN_FILE] = String.valueOf(depositProfit);
                    }
                    lineFromFile = "";
                    /*Cycle to fill our string with String[] splitLineFromFile*/
                    for (String aSplitLineFromFile : splitLineFromFile) {
                        lineFromFile += aSplitLineFromFile + " ";
                    }
                }
                listOfLines.add(lineFromFile);
            }
        }
		/*Clean file*/
        FileCleaner.cleanFile(Constants.ACCOUNT_FILE_PATH);
		/*Writing in file line by line*/
        try (FileWriter writer = new FileWriter(Constants.ACCOUNT_FILE_PATH, true)) {
            for (String s : listOfLines) {
                writer.write(s + "\r" + "\n");
            }
        }
	}

	/**
	 * This method changes term of Deposit in File
     * @param accountId unique Account id
     * @param pastTerm it indicates how many months of the deposit remains
     * @param depositProfit income that the client received
	 */
	public void changeTerm(int accountId, int pastTerm, int depositProfit) throws IOException {
		Account account = Account.getAccountById(accountId);
		List<String> listOfLines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.ACCOUNT_FILE_PATH))) {
		    /*This "while" reads lines from file*/
            while (fileReader.ready()) {
                String lineFromFile = fileReader.readLine();
                /*Split lineFromFile by spaces*/
                String[] splitLineFromFile = lineFromFile.split(" ");
                int accountInFileID = Integer.parseInt(splitLineFromFile[Constants.ACCOUNT_IN_FILE_ID]);
                int accountToChangeID = account.getAccountId();
                if (accountInFileID == accountToChangeID) {
                    /*Change the term of deposit*/
                    splitLineFromFile[Constants.TERM_IN_FILE] = String.valueOf(pastTerm);
                    /*Change deposit profit*/
                    splitLineFromFile[Constants.DEPOSIT_PROFIT_IN_FILE] = String.valueOf(depositProfit);
                }
                lineFromFile = "";
                /*Cycle to fill our string with String[] splitLineFromFile*/
                for (int j = 0; j < splitLineFromFile.length; j++) {
                    lineFromFile += splitLineFromFile[j] + " ";
                }
                listOfLines.add(lineFromFile);
            }
        }
		/*Clean file*/
        FileCleaner.cleanFile(Constants.ACCOUNT_FILE_PATH);
        /*Writing in file line by line*/
		try(FileWriter writer = new FileWriter(Constants.ACCOUNT_FILE_PATH, true)) {
            for (String s : listOfLines) {
                writer.write(s + "\r" + "\n");
            }
        }
	}

	/**This method delete's account from file
     * @param accountId unique Account id
     */
	public void deleteAccount(int accountId) throws IOException {
        int accountInFileID;
        List<String> listOfLines = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(Constants.ACCOUNT_FILE_PATH))){
		/*This "while" reads lines from file*/
        while (fileReader.ready()) {
            String lineFromFile = fileReader.readLine();
			/*Split lineFromFile by spaces*/
            String[] splitLineFromFile = lineFromFile.split(" ");
            try {
                accountInFileID = Integer.parseInt(splitLineFromFile[Constants.ACCOUNT_IN_FILE_ID]);
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
                continue;
            }
            if (accountInFileID == accountId) {
                lineFromFile = "";
            }
            listOfLines.add(lineFromFile);
        }
    }
        /*clean file*/
        FileCleaner.cleanFile(Constants.ACCOUNT_FILE_PATH);
        /*Writing in file line by line*/
        try (FileWriter writer = new FileWriter(Constants.ACCOUNT_FILE_PATH, true)) {
            for (String s : listOfLines) {
                if (s.equals("")){
                    continue;
                }
                writer.write(s + "\r" + "\n");
            }
        }
	}
}
