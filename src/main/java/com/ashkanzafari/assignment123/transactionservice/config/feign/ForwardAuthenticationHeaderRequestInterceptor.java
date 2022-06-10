package com.ashkanzafari.assignment123.transactionservice.config.feign;

import com.ashkanzafari.assignment123.transactionservice.jwt.CustomAuthenticationToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ForwardAuthenticationHeaderRequestInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER="Authorization";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication instanceof CustomAuthenticationToken) {
      CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
      requestTemplate.header(AUTHORIZATION_HEADER, String.format(customAuthenticationToken.getOriginalToken()));
    }
  }
}
