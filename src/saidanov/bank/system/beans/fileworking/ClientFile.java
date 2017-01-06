package saidanov.bank.system.beans.fileworking;

import saidanov.bank.system.beans.account.Account;
import saidanov.bank.system.beans.interfaces.FileChangeAbility;

import java.io.IOException;

/**
 * Created by Godfrid on 06.01.2017.
 */
public class ClientFile implements FileChangeAbility {

	/**
	 * This method adds new Client to File
	 *
	 * @param account
	 */
	@Override
	public void addToFile(Account account) throws IOException {
		//TODO
	}


	@Override
	public void changeAmountOfMoney(Account account) throws IOException {
		try {
			throw new NoSuchMethodException();
		} catch (NoSuchMethodException e) {
			System.out.println("This method not allow there. You can call this method from AccountFile class");
		}
	}

	@Override
	public void changeTerm(int accountId, int pastTerm, int depositProfit) throws IOException {
		try {
			throw new NoSuchMethodException();
		} catch (NoSuchMethodException e) {
			System.out.println("This method not allow there. You can call this method from AccountFile class");
		}
	}

	@Override
	public void deleteAccount(int accountId) throws IOException {
		try {
			throw new NoSuchMethodException();
		} catch (NoSuchMethodException e) {
			System.out.println("This method not allow there. You can call this method from AccountFile class");
		}
	}
}
