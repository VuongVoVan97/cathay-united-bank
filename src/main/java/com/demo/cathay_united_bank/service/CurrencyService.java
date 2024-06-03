package com.demo.cathay_united_bank.service;

import com.demo.cathay_united_bank.entity.Currency;
import com.demo.cathay_united_bank.exception.CurrencyException;

import java.util.List;

public interface CurrencyService {

    Currency saveCurrency(Currency currency);
    void deleteCurrency(String code) throws CurrencyException;
    List<Currency> getAllCurrencies();
    Currency findByCode(String code) throws CurrencyException;
}
