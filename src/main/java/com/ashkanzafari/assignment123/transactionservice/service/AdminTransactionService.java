package com.ashkanzafari.assignment123.transactionservice.service;

import com.ashkanzafari.assignment123.transactionservice.dto.request.DepositRequestDto;
import com.ashkanzafari.assignment123.transactionservice.dto.response.DepositResponseDto;
import com.ashkanzafari.assignment123.transactionservice.exception.AccountNotFoundException;
import com.ashkanzafari.assignment123.transactionservice.feign.AccountClient;
import com.ashkanzafari.assignment123.transactionservice.model.Transaction;
import com.ashkanzafari.assignment123.transactionservice.repository.TransactionRepositoray;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AdminTransactionService {

  private final TransactionRepositoray transactionRepositoray;
  private final BalanceService balanceService;
  private final AccountService accountService;

  @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class,
          propagation = Propagation.REQUIRED)
  public DepositResponseDto deposit(final DepositRequestDto depositRequestDto) throws
          AccountNotFoundException {

    //check with account-service to see if the account exists
    accountService.validateAccount(depositRequestDto.getToAccount());

    //add transaction record to DB
    Transaction depositTransaction = Transaction.createDepositTransaction(depositRequestDto.getToAccount(),
            depositRequestDto.getAmount());
    transactionRepositoray.save(depositTransaction);

    //add the amount to the account balance
    balanceService.deposit(depositRequestDto.getToAccount(), depositRequestDto.getAmount(), depositTransaction);

    DepositResponseDto depositResponseDto = new DepositResponseDto(depositRequestDto.getToAccount(),
            depositRequestDto.getAmount(), true);
    return depositResponseDto;
  }

}
