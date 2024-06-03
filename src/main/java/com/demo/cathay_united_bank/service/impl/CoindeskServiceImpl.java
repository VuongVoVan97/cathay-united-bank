package com.demo.cathay_united_bank.service.impl;

import com.demo.cathay_united_bank.entity.Currency;

import com.demo.cathay_united_bank.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Service
@EnableScheduling
public class CoindeskServiceImpl {


    private CoindeskServiceImpl() {
    }

    private static CoindeskServiceImpl instance;

    @Autowired
    private CurrencyService currencyService;

    @Value("${coin-desk-service.url}")
    private String url;

    public static synchronized CoindeskServiceImpl getInstance() {
        if (instance == null) {
            instance = new CoindeskServiceImpl();
        }
        return instance;
    }

    @Scheduled(fixedRate = 3600000)
    public void syncCoindeskData() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null) {
            Map<String, Object> bpi = (Map<String, Object>) response.get("bpi");
            bpi.forEach((key, value) -> {
                Map<String, Object> data = (Map<String, Object>) value;
                String code = (String) data.get("code");
                String description = (String) data.get("description");
                String rate = (String) data.get("rate");
                double rateFloat = (double) data.get("rate_float");
                Currency currency = new Currency();
                currency.setCode(code);
                currency.setDescription(description);
                currency.setRate(rate);
                currency.setRateFloat(rateFloat);
                currency.setUpdateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                currencyService.saveCurrency(currency);
            });
        }

    }

}
