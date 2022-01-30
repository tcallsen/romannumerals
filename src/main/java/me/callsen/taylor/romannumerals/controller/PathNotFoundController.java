package me.callsen.taylor.romannumerals.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.callsen.taylor.romannumerals.model.ErrorResponse;

@RestController
public class PathNotFoundController implements ErrorController {
  
  @GetMapping("/error")
  public ErrorResponse handleInvalidRequestParameter() {
    return new ErrorResponse(404, "requested path not found");
  }
  
}
