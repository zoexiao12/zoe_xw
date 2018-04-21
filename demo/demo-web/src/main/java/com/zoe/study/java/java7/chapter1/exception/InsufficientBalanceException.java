package com.zoe.study.java.java7.chapter1.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends LocalizedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1700003106050910338L;
	
	
	private BigDecimal requested;
	private BigDecimal balance;
	private BigDecimal shortage;

	public InsufficientBalanceException(BigDecimal requested,BigDecimal balance){
		super("INSUFFICIENT_BALANCE_EXCEPTION");
		this.requested = requested;
		this.balance = balance;
		this.shortage = requested.subtract(balance);
		
	}
	@Override
	public String getLocalizedMessage() {
		return format(balance,requested,shortage);
	}

}
