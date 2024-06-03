package com.demo.cathay_united_bank;

import com.demo.cathay_united_bank.service.impl.CoindeskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class CathayUnitedBankApplication {


	public static void main(String[] args) {
		SpringApplication.run(CathayUnitedBankApplication.class, args);
	}


}
