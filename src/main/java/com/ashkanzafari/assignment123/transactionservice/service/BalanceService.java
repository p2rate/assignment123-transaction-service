package com.ashkanzafari.assignment123.transactionservice.service;

import com.ashkanzafari.assignment123.transactionservice.exception.AccountNotFoundException;
import com.ashkanzafari.assignment123.transactionservice.exception.NotEnoughFundsException;
import com.ashkanzafari.assignment123.transactionservice.exception.UnauthorizedException;
import com.ashkanzafari.assignment123.transactionservice.model.Balance;
import com.ashkanzafari.assignment123.transactionservice.model.Transaction;
import com.ashkanzafari.assignment123.transactionservice.repository.BalanceRepositoray;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceService {

  private final BalanceRepositoray balanceRepositoray;
  private final AccountService accountService;

  /**
   * Service layer method to deposit credit to an account
   *
   * @param toAccount
   * @param amount
   * @return
   * @throws AccountNotFoundException
   * @throws NotEnoughFundsException
   */
  public Balance deposit(String toAccount, Double amount, Transaction transaction) {

    Optional<Balance> balance = balanceRepositoray.findByAccountAndDeletedIsFalse(toAccount);
    Balance dbBalance;

    if (balance.isEmpty()) {
      dbBalance = new Balance(toAccount, amount);
    } else {
      dbBalance = balance.get();
      Double newAmount = dbBalance.getBalance() + amount;
      dbBalance.setBalance(newAmount);
    }

    dbBalance.getTransactions().add(transaction);
    return balanceRepositoray.save(dbBalance);
  }

  /**
   * Service layer method to withdraw credit from an account
   * @param fromAccount
   * @param amount
   * @return
   * @throws AccountNotFoundException
   * @throws NotEnoughFundsException
   */
  public Balance withdrawal(String fromAccount, Double amount) throws AccountNotFoundException,
          NotEnoughFundsException {

    Optional<Balance> balance = balanceRepositoray.findByAccountAndDeletedIsFalse(fromAccount);
    Balance dbBalance;

    if (balance.isEmpty()) {
      throw new AccountNotFoundException(String.format("Account: %s not found", fromAccount));
    }

    dbBalance = balance.get();
    if(dbBalance.getBalance() < amount){
      throw new NotEnoughFundsException(String.format("Account: %s does not have enough funds", fromAccount));
    }
    Double newAmount = dbBalance.getBalance() - amount;
    dbBalance.setBalance(newAmount);


    return balanceRepositoray.save(dbBalance);
  }

  /**
   * Service layer method to get account's info. The method verifies the authenticated user
   * is the owner of the requested accountId
   *
   * @param accountId
   * @param authenticatedUser
   * @return
   */
  public Balance getBalanceByAccountId(String accountId, String authenticatedUser) throws
          AccountNotFoundException, UnauthorizedException {

    //check with account-service if the authenticated user is the owner of this account
    accountService.validateRequest(accountId, authenticatedUser);

    return balanceRepositoray.findByAccountAndDeletedIsFalse(accountId).orElse(new Balance(accountId, 0D));
  }
}
