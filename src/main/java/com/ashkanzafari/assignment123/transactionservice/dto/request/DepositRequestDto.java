package com.ashkanzafari.assignment123.transactionservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositRequestDto {

  private String toAccount;
  private Double amount;
}
