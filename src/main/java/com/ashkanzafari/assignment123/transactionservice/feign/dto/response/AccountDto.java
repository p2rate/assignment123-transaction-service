package com.ashkanzafari.assignment123.transactionservice.feign.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Permission.
 *
 * <p>Class to persist what permissions each Role has</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {

  private String accountId;

  private String userId;

}
