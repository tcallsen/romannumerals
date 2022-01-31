package me.callsen.taylor.romannumerals.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExceptionController.class)
public class ExceptionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void verifyBasicTest() throws Exception {
    this.mockMvc.perform(get("/invalidpath"))
            .andExpect(status().isNotFound());
  }

}
