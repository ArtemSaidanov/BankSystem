package by.saidanov.bank.exceptions;

/**
 * NotEnoughMoneyException
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * This exception throws when Client try to takeMoney,
 * but there is not enough money on that Account
 */
public class NotEnoughMoneyException extends Exception {
	public NotEnoughMoneyException(String s) {
		super();
		System.out.println(s);
	}
}
