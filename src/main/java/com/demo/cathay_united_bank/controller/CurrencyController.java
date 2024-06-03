package com.demo.cathay_united_bank.controller;

import com.demo.cathay_united_bank.entity.Currency;
import com.demo.cathay_united_bank.exception.CurrencyException;
import com.demo.cathay_united_bank.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable String id) throws CurrencyException {
        return currencyService.findByCode(id);
    }

    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.saveCurrency(currency);
    }

    @PutMapping("/{id}")
    public Currency updateCurrency(@PathVariable String id, @RequestBody Currency currency) {
        currency.setCode(id);
        return currencyService.saveCurrency(currency);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable String id) throws CurrencyException {
        currencyService.deleteCurrency(id);
    }
}
