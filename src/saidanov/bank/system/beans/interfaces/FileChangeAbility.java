package saidanov.bank.system.beans.interfaces;

import saidanov.bank.system.beans.account.Account;

import java.io.IOException;

/**
 * FileChangeAbility
 *
 * @version 1.0
 *
 * Date 06.01.2017
 *
 * This interface has methods to work with files
 */
public interface FileChangeAbility {

	/**
	 * This method adds new Account to File
	 */
	void addToFile(Account account) throws IOException;

	/**
	 * This method changes the account's amount of money in the File
	 * This method calls when Client takes money from the account or puts money into the account
	 */
	void changeAmountOfMoney(Account account) throws IOException;

	/**
	 * This method changes term of Deposit in File
	 */
	void changeTerm(int accountId, int pastTerm, int depositProfit) throws IOException;

	/**
	 * This method delete's account from file
	 */
	void deleteAccount(int accountId) throws IOException;
}
