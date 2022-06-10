package com.ashkanzafari.assignment123.transactionservice.feign;

import com.ashkanzafari.assignment123.transactionservice.dto.response.ApiResponse;
import com.ashkanzafari.assignment123.transactionservice.feign.dto.response.AccountDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * AccountClient.
 *
 * <p>Feign client for communicating with account-service</p>
 */
@FeignClient(
    value = "AccountClient",
    url = "${services.account.base-url}"
)
public interface AccountClient {

    /**
     * Method that is used to ensure an account is valid
     *
     * @return  List of Destinations
     */
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/account/{accountId}",
        consumes = "application/json"
    )
    @Headers("Content-Type: application/json")
    ApiResponse<AccountDto> getAccount(final @PathVariable String accountId);
}
