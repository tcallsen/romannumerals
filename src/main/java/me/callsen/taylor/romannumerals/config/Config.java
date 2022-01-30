package me.callsen.taylor.romannumerals.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class Config {

  @Bean
  public HttpTraceRepository httpTraceRepository() {
      return new InMemoryHttpTraceRepository();
  }

  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
      return new MethodValidationPostProcessor();
  }

}
