package me.callsen.taylor.romannumerals.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
* Defines Spring related configuration options for this API.
*/
@Configuration
public class Config {

  /**
  * Enables HTTP request trace logging usage via the Spring Actuator.
  */
  @Bean
  public HttpTraceRepository httpTraceRepository() {
      return new InMemoryHttpTraceRepository();
  }

  /**
  * Enables request parameter validation in controllers.
  */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
      return new MethodValidationPostProcessor();
  }

}
