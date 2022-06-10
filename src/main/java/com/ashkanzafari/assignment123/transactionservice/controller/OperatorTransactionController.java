package com.ashkanzafari.assignment123.transactionservice.controller;

import com.ashkanzafari.assignment123.transactionservice.dto.request.DepositRequestDto;
import com.ashkanzafari.assignment123.transactionservice.dto.response.ApiResponse;
import com.ashkanzafari.assignment123.transactionservice.dto.response.DepositResponseDto;
import com.ashkanzafari.assignment123.transactionservice.service.AdminTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/operator/v1/transaction")
@RequiredArgsConstructor
public class OperatorTransactionController {

  private final AdminTransactionService adminTransactionService;

  /**
   * The endpoint to deposit credit to an account. The endpoint requires operator access
   *
   * @return
   */
  @PreAuthorize("hasAuthority('WriteLev2')")
  @PostMapping(
          path = "/deposit",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ApiResponse<DepositResponseDto> deposit(@RequestBody @Valid DepositRequestDto depositRequestDto){

    DepositResponseDto depositResponseDto = adminTransactionService.deposit(depositRequestDto);
    return new ApiResponse<>(depositResponseDto, HttpStatus.OK, "OK");
  }

}
