package com.ashkanzafari.assignment123.transactionservice.service;

import com.ashkanzafari.assignment123.transactionservice.dto.response.ApiResponse;
import com.ashkanzafari.assignment123.transactionservice.exception.AccountNotFoundException;
import com.ashkanzafari.assignment123.transactionservice.exception.UnauthorizedException;
import com.ashkanzafari.assignment123.transactionservice.feign.AccountClient;
import com.ashkanzafari.assignment123.transactionservice.feign.dto.response.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountClient accountClient;


  /**
   * Service layer method to validate the accountId and verify that the authenticated user is
   * owner of the account
   *
   * @param accountId
   * @param userId
   * @throws AccountNotFoundException
   * @throws UnauthorizedException
   */
  public void validateRequest(String accountId, String userId) throws AccountNotFoundException,
          UnauthorizedException {

    ApiResponse<AccountDto> validateAccount = accountClient.getAccount(accountId);
    if (validateAccount.getPayload() == null) {
      throw new AccountNotFoundException(String.format("account: %s could not be found", accountId));
    }
    if (!validateAccount.getPayload().getUserId().equals(userId)) {
      throw new UnauthorizedException(String.format("account: %s does not belong to this user",accountId));
    }
  }

  /**
   * Service layer method to validate the accountId exists
   *
   * @param accountId
   * @throws AccountNotFoundException
   */
  public void validateAccount(String accountId) throws AccountNotFoundException {

    ApiResponse<AccountDto> validateAccount = accountClient.getAccount(accountId);
    if (validateAccount.getPayload() == null) {
      throw new AccountNotFoundException(String.format("account: %s could not be found", accountId));
    }
  }
}
