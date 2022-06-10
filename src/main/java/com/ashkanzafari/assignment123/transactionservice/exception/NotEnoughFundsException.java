package com.ashkanzafari.assignment123.transactionservice.exception;

public class NotEnoughFundsException extends RuntimeException{

  public NotEnoughFundsException() {
  }

  public NotEnoughFundsException(String message) {
    super(message);
  }

  public NotEnoughFundsException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotEnoughFundsException(Throwable cause) {
    super(cause);
  }
}
