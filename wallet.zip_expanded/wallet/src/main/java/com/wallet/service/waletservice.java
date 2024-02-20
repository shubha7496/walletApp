package com.wallet.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wallet.entity.Transaction;


@FeignClient(name = "TRANSACTION-SERVICE")
public interface waletservice {

	@GetMapping("/transaction/wallet/{walletid}")
	public List<Transaction> getBywalletid(@PathVariable String walletid);
}
