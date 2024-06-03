package com.demo.cathay_united_bank.controller;

import com.demo.cathay_united_bank.entity.Currency;
import com.demo.cathay_united_bank.service.CurrencyService;
import com.demo.cathay_united_bank.service.impl.CoindeskServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllCurrencies() throws Exception {
        Currency currency1 = new Currency();
        currency1.setCode("USD");
        currency1.setRate("1.0");

        Currency currency2 = new Currency();
        currency2.setCode("EUR");
        currency2.setRate("2.0");

        Mockito.when(service.getAllCurrencies()).thenReturn(Arrays.asList(currency1, currency2));

        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("USD"))
                .andExpect(jsonPath("$[1].code").value("EUR"));
    }

    @Test
    public void shouldReturnCurrencyById() throws Exception {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setRate("2.0");

        Mockito.when(service.findByCode(Mockito.anyString())).thenReturn(currency);

        mockMvc.perform(get("/currencies/USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    public void shouldAddCurrency() throws Exception {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setRate("2.0");

        Mockito.when(service.saveCurrency(Mockito.any(Currency.class))).thenReturn(currency);

        mockMvc.perform(post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    public void shouldUpdateCurrency() throws Exception {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setRate("1.0");

        Mockito.when(service.saveCurrency(Mockito.any(Currency.class))).thenReturn(currency);

        mockMvc.perform(put("/currencies/USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    public void shouldDeleteCurrency() throws Exception {
        mockMvc.perform(delete("/currencies/USD"))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).deleteCurrency("USD");
    }
}
