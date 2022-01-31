package me.callsen.taylor.romannumerals.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.callsen.taylor.romannumerals.model.ErrorResponse;

/**
* Handles error requests and returns a 404 status code and message.
*/
@RestController
public class ExceptionController implements ErrorController {
  
  /**
  * Handlers error requests and returns a 404 status code and message.
  *
  * @return POJO containing an error response status code and message.
  */
  @GetMapping("/error")
  public ErrorResponse handleInvalidPath() {
    return new ErrorResponse(404, "requested path not found");
  }
  
}
