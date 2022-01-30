package me.callsen.taylor.romannumerals.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import me.callsen.taylor.romannumerals.model.Conversion;
import me.callsen.taylor.romannumerals.service.ConversionService;

@WebMvcTest(ConversionController.class)
public class ConversionControllerTest {

  @Autowired
	private MockMvc mockMvc;

  @MockBean
	private ConversionService conversionService;

  @BeforeEach
  public void setup() {
    when(conversionService.convertDecimalToNumeral(anyInt())).thenAnswer(answer -> {
      return new Conversion(answer.getArgument(0), "output-value");
    });
  }

  @Test
  public void verifyBasicTest() throws Exception {
    this.mockMvc.perform(get("/romannumeral?query=4"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.inputValue").value("4"))
            .andExpect(jsonPath("$.outputValue").value("output-value"));
  }

  @Test
  public void verifyMissingQueryString() throws Exception {
    this.mockMvc.perform(get("/romannumeral"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400));
  }

  @Test
  public void verifyInvalidQueryString() throws Exception {
    this.mockMvc.perform(get("/romannumeral?query=invalud"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400));
  }

  @Test
  public void verifyInvalidQueryStringLow() throws Exception {
    this.mockMvc.perform(get("/romannumeral?query=0"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400));
  }

  @Test
  public void verifyInvalidQueryStringHigh() throws Exception {
    this.mockMvc.perform(get("/romannumeral?query=99999999"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400));
  }

  @Test
  public void verifyInvalidPath() throws Exception {
    this.mockMvc.perform(get("/notfound"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void verifyInvalidHttpMethod() throws Exception {
    this.mockMvc.perform(post("/romannumeral?query=4"))
            .andExpect(status().isMethodNotAllowed());
  }

}
