package by.saidanov.bank.exceptions;

/**
 * TermCanNotRaiseException
 *
 * @version  1.0
 *
 * Date 29.12.2016
 *
 * This exception throws when Client try to raise term of his deposit.
 */
public class TermCanNotRaiseException extends Exception{
	public TermCanNotRaiseException(String s) {
		System.out.println(s);
	}
}
