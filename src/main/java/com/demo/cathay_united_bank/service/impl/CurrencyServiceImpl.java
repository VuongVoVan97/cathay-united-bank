package com.demo.cathay_united_bank.service.impl;

import com.demo.cathay_united_bank.entity.Currency;
import com.demo.cathay_united_bank.exception.CurrencyException;
import com.demo.cathay_united_bank.repository.CurrencyRepository;
import com.demo.cathay_united_bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;


    @Override
    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(String code) throws CurrencyException {
        if (code == null) {
            throw new CurrencyException("Currency id is null");
        }
        currencyRepository.deleteById(code);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency findByCode(String code) throws CurrencyException {

        return currencyRepository.findById(code).
                orElseThrow(() ->  new CurrencyException("Currency id is not found"));
    }
}
