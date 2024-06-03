package com.demo.cathay_united_bank.service.impl;

import com.demo.cathay_united_bank.entity.Currency;
import com.demo.cathay_united_bank.exception.CurrencyException;
import com.demo.cathay_united_bank.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyServiceImpl currencyService;


    @Test
    public void testGetAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", "US Dollar","" , 1.0, "1.0"));
        currencies.add(new Currency("EUR", "Euro","" , 1.0, "1.0"));

        when(currencyRepository.findAll()).thenReturn(currencies);

        List<Currency> result = currencyService.getAllCurrencies();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetCurrencyById() throws CurrencyException {
        Currency currency = new Currency("USD", "US Dollar","" , 1.0, "1.0");

        when(currencyRepository.findById("USD")).thenReturn(Optional.of(currency));

        Currency result = currencyService.findByCode("USD");

        assertEquals("USD", result.getCode());
    }

    @Test
    public void testAddCurrency() {
        Currency currency = new Currency("USD", "US Dollar","" , 1.0, "1.0");

        when(currencyRepository.save(currency)).thenReturn(currency);

        Currency result = currencyService.saveCurrency(currency);

        assertEquals("USD", result.getCode());
    }

    @Test
    public void testUpdateCurrency() {
        Currency currency = new Currency("USD", "US Dollar","" , 1.0, "1.0");

        when(currencyRepository.save(currency)).thenReturn(currency);

        Currency result = currencyService.saveCurrency(currency);

        assertEquals("USD", result.getCode());
    }

    @Test
    public void testDeleteCurrency() throws CurrencyException {
        currencyService.deleteCurrency("USD");

        verify(currencyRepository, times(1)).deleteById("USD");
    }
}
