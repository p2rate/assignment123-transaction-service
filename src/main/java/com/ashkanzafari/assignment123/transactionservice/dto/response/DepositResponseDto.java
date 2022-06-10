package com.ashkanzafari.assignment123.transactionservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositResponseDto {

  private String toAccount;
  private Double amount;
  private Boolean success;
}
