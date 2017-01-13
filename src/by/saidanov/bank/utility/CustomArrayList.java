package by.saidanov.bank.utility;

import by.saidanov.bank.beans.account.Account;

import java.util.ArrayList;

/**
 * CustomArrayList
 *
 * @version 1.0
 *
 * Date 06.01.2017
 *
 * This class overrides method "get" to forbid access to deleted accounts
 */
public class CustomArrayList <A> extends ArrayList {

	@Override
	public Object get(int index) throws IllegalArgumentException{
		if (super.get(index) instanceof Account){
			Account account = (Account) super.get(index);
			if (account.getAccountId() < 0) {
				throw new IllegalArgumentException("Account ID can not be < 0");
			}
		}
		return super.get(index);
	}
}
