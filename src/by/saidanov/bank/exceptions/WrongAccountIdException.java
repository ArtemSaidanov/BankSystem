package by.saidanov.bank.exceptions;

/**
 * WrongAccountIdException
 *
 * @version 1.0
 *
 * Date 06.01.2017
 *
 * This exception throws when you try to het account with id < 0
 */
public class WrongAccountIdException extends Exception {
	public WrongAccountIdException(String s) {
		System.out.println(s);
	}
}
