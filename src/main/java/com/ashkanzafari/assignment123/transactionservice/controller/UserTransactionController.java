package com.ashkanzafari.assignment123.transactionservice.controller;

import com.ashkanzafari.assignment123.transactionservice.dto.request.DepositRequestDto;
import com.ashkanzafari.assignment123.transactionservice.dto.response.ApiResponse;
import com.ashkanzafari.assignment123.transactionservice.dto.response.DepositResponseDto;
import com.ashkanzafari.assignment123.transactionservice.model.Balance;
import com.ashkanzafari.assignment123.transactionservice.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class UserTransactionController {

  private final BalanceService balanceService;


  /**
   * Endpoint to get account info by accountId. the endpoint verifies that the authenticated
   * user is owner of the requested accountId
   *
   * @param accountId
   * @param principal
   * @return
   */
  @PreAuthorize("hasAuthority('WriteLev1')")
  @GetMapping(path = "/balance/{accountId}")
  public ApiResponse<Balance> getByAccountId(@PathVariable final String accountId,
                                             final Principal principal) {

    Balance balance = balanceService.getBalanceByAccountId(accountId, principal.getName());
    return new ApiResponse<>(balance, HttpStatus.OK, "OK");
  }
}
