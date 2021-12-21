package subsystem;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import entity.*;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(entity.CreditCard, int,
	 *      java.lang.String)
	 */
	public Transaction payOrder(CreditCard card, int amount, String contents) {
		Transaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(entity.CreditCard, int,
	 *      java.lang.String)
	 */
	public Transaction refund(CreditCard card, int amount, String contents) {
		Transaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
