package by.saidanov.bank.beans.interfaces;

import by.saidanov.bank.exceptions.NotEnoughMoneyException;
import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.exceptions.TermCanNotRaiseException;

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
