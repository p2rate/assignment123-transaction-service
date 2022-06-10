package com.ashkanzafari.assignment123.transactionservice.exception;


public class AccountNotFoundException extends RuntimeException{

  public AccountNotFoundException() {
  }

  public AccountNotFoundException(String message) {
    super(message);
  }

  public AccountNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public AccountNotFoundException(Throwable cause) {
    super(cause);
  }
}
