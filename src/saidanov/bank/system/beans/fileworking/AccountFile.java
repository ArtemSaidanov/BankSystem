package saidanov.bank.system.beans.fileworking;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.account.Deposit;
import saidanov.bank.system.beans.interfaces.FileChangeAbility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountFile
 *
 * @version 1.0
 *
 * Date 04.01.2017
 *
 * This class works with files like with Database
 * We have two files: AccountFile and ClientFile
 * AccountFile includes list of account with all account's parameters
 * Client includes list of clients with all client's parameters
 */
public class AccountFile implements FileChangeAbility {

	private String accountFilePath = "F:\\projects\\BankSystem\\src\\AccountFile.txt";
	private String clientFile = "F:\\projects\\BankSystem\\src\\ClientFile.txt";

	/**
	 * This method adds new Account to File
	 */
	public void addToFile(Account account) throws IOException {
		FileWriter fileWriter = new FileWriter(accountFilePath, true);
		if (account instanceof Deposit) {
			fileWriter.write(account.getAccountId() + " - Account ID" + " Client ID: " + account.getClientId() + " Amount of money: " + account.getAmountOfMoney() + " term: " + ((Deposit) account).getTerm() + " persentage: " + ((Deposit) account).getPersentage() + " deposit profit: " + ((Deposit) account).getDepositProfit() + " currency: " + ((Deposit) account).getCurrency() + "\r" + "\n");
		} else
			fileWriter.write(account.getAccountId() + " - Account ID" + " Client ID: " + account.getClientId() + " Amount of money: " + account.getAmountOfMoney() + "\r" + "\n");
		fileWriter.close();
	}

	/**
	 * This method changes the account's amount of money in the File
	 * This method calls when Client takes money from the account or puts money into the account
	 */
	public void changeAmountOfMoney(Account account) throws IOException {
		final int amountOfMoneyInFile = 10;
		final int depositProfitInFile = 17;
		int accountInFileID;
		BufferedReader fileReader = new BufferedReader(new FileReader(accountFilePath));

		List<String> listOfLines = new ArrayList<>();
		/**
		 * This "while" reads lines from file*/
		while (fileReader.ready()) {
			String lineFromFile = fileReader.readLine();
			/**Split lineFromFile by spaces*/
			String[] splitLineFromFile = lineFromFile.split(" ");
			try {
				accountInFileID = Integer.parseInt(splitLineFromFile[0]);
			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException");
				continue;
			}
			int accountToChangeID = account.getAccountId();
			if (accountInFileID == accountToChangeID) {
				/**Change the value of amountOfMoney*/
				splitLineFromFile[amountOfMoneyInFile] = String.valueOf(account.getAmountOfMoney());
				if (account instanceof Deposit) {
					int depositProfit = Integer.parseInt(splitLineFromFile[depositProfitInFile]) / 2;
					splitLineFromFile[depositProfitInFile] = String.valueOf(depositProfit);
				}
				lineFromFile = "";
				/**Cycle to fill our string with String[] splitLineFromFile*/
				for (int j = 0; j < splitLineFromFile.length; j++) {
					lineFromFile += splitLineFromFile[j] + " ";
				}
			}
			listOfLines.add(lineFromFile);
		}
		/**Clean file*/
		FileWriter fileCleaner = new FileWriter(accountFilePath);
		fileCleaner.write("");
		fileCleaner.close();
		/**Writing in file line by line*/
		FileWriter writer = new FileWriter(accountFilePath, true);
		for (String s : listOfLines) {
			writer.write(s + "\r" + "\n");
		}

		writer.close();
		fileReader.close();
	}

	/**
	 * This method changes term of Deposit in File
	 */
	public void changeTerm(int accountId, int pastTerm, int depositProfit) throws IOException {
		final int termInFile = 12;
		final int depositProfitInFile = 17;
		BufferedReader fileReader = new BufferedReader(new FileReader(accountFilePath));
		Account account = Account.getAccountById(accountId);
		List<String> listOfLines = new ArrayList<>();

		/**
		 * This "while" reads lines from file*/
		while (fileReader.ready()) {
			String lineFromFile = fileReader.readLine();
			/**Split lineFromFile by spaces*/
			String[] splitLineFromFile = lineFromFile.split(" ");
			int accountInFileID = Integer.parseInt(splitLineFromFile[0]);
			int accountToChangeID = account.getAccountId();
			if (accountInFileID == accountToChangeID) {
				/**Change the term of deposit*/
				splitLineFromFile[termInFile] = String.valueOf(pastTerm);
				/**Change deposit profit*/
				splitLineFromFile[depositProfitInFile] = String.valueOf(depositProfit);
			}
			lineFromFile = "";
			/**Cycle to fill our string with String[] splitLineFromFile*/
			for (int j = 0; j < splitLineFromFile.length; j++) {
				lineFromFile += splitLineFromFile[j] + " ";
			}
			listOfLines.add(lineFromFile);
		}
		/**Clean file*/
		FileWriter fileCleaner = new FileWriter(accountFilePath);
		fileCleaner.write("");
		fileCleaner.close();
		/**Writing in file line by line*/
		FileWriter writer = new FileWriter(accountFilePath, true);
		for (String s : listOfLines) {
			writer.write(s + "\r" + "\n");
		}

		writer.close();
		fileReader.close();
	}

	/**
	 * This method delete's account from file
	 */
	public void deleteAccount(int accountId) throws IOException {
		int accountInFileID;
		BufferedReader fileReader = new BufferedReader(new FileReader(accountFilePath));

		List<String> listOfLines = new ArrayList<>();
		/**
		 * This "while" reads lines from file*/
		while (fileReader.ready()) {
			String lineFromFile = fileReader.readLine();
			/**Split lineFromFile by spaces*/
			String[] splitLineFromFile = lineFromFile.split(" ");
			try {
				accountInFileID = Integer.parseInt(splitLineFromFile[0]);
			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException");
				continue;
			}
			if (accountInFileID == accountId) {
				lineFromFile = "";
			}
			listOfLines.add(lineFromFile);
		}
		/**Clean file*/
		FileWriter fileCleaner = new FileWriter(accountFilePath);
		fileCleaner.write("");
		fileCleaner.close();
		/**Writing in file line by line*/
		FileWriter writer = new FileWriter(accountFilePath, true);
		for (String s : listOfLines) {
			if (s.equals("")){
				continue;
			}
			writer.write(s + "\r" + "\n");
		}

		writer.close();
		fileReader.close();
	}
}
