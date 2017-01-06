package saidanov.bank.system.beans;

import saidanov.bank.system.exceptions.WrongAccountIdException;

/**
 * Validator
 *
 * @version 1.0
 *
 * Date 06.01.2017
 *
 * This class validate account ID
 */
public class Validator {

	public static void accountIDValidation(int accountID) throws WrongAccountIdException{
		if (accountID < 0){
			throw new WrongAccountIdException("Account ID can't be < 0");
		}
	}


}
