package saidanov.bank.system.beans.interfaces;

import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.exceptions.NotEnoughMoneyException;
import saidanov.bank.system.exceptions.TermCanNotRaiseException;

import java.io.IOException;

/**
 * AccountChangeAbility
 *
 * Version 1.0
 *
 * Date 28.12.2016
 *
 * This interface has two methods to create an Account. This methods will be using in Client and Manager classes
 */
public interface AccountChangeAbility {

	/**
	 * <p>Method for creating an Account</p>
	 * <p>Manager calls AccountFactory to create an account</p>
	 * @param clientId unique id of Client
	 * @param initialContribution is the amount of money that Client put into the account at first time
	 */
	Account createAccount(int clientId,int initialContribution);

	/**
	 * <p>Method for creating a Deposit</p>
	 * <p>Manager calls AccountFactory to create an account</p>
	 * @param clientId unique id of Client
	 * @param initialContribution is the amount of money that Client put into the account at first time
	 * @param term the amount of months which the deposit will be kept in the bank
	 * @param persentage amount of interest that the client will receive per months
	 */
	Account createAccount(int clientId, int initialContribution, int term,
						  double persentage, DepositCurrency currency);

	/**
	 * <p>Method allows the Client to take money from his account</p>
	 * @param accountId unique accountId
	 * @param money money that Client decided to take
	 */
	void takeMoney(int accountId, int money) throws NotEnoughMoneyException;

	/**
	 * This method puts money into the account
	 * @param accountId unique accountId
	 * @param money money that Client decided to take
	 */
	void putMoney(int accountId, int money);

	/**
     * This method deletes account by ID
     */
	void deleteAccount(int accountId) throws IOException;

    /**
     * This method changes term of deposit
     * @param pastTerm it indicates how many months of the deposit remains
     */
	void setTerm(Account account, int pastTerm) throws IOException, TermCanNotRaiseException;
}
