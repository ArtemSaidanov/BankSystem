package saidanov.bank.system.beans.interfaces;

import saidanov.bank.system.beans.DepositCurrency;
import saidanov.bank.system.beans.account.Account;

/**
 * CreateAccount
 *
 * Version 1.0
 *
 * Date 28.12.2016
 *
 * This interface has two methods to create an Account. This methods will be using in Client and Manager classes
 */
public interface CreateAccount {

	Account createAccount(int clientId,int initialContribution);

	Account createAccount(int clientId, int initialContribution, int term,
						  double persentage, DepositCurrency currency);

}
