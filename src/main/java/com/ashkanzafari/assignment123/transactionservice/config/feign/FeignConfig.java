package com.ashkanzafari.assignment123.transactionservice.config.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  public RequestInterceptor basicAuthRequestInterceptor() {
    return new ForwardAuthenticationHeaderRequestInterceptor();
  }
}
