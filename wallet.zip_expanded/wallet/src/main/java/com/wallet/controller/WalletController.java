package com.wallet.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.entity.Transaction;
import com.wallet.entity.Wallet;
import com.wallet.service.WalletService;



@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	private WalletService service;
	
//	@Autowired
//	private TransactionService service2;
	
	
	@PostMapping("/create")
	public Wallet create(@RequestBody Wallet wallet) {
		return service.create(wallet);
	}

	
	@GetMapping("/{walletid}")
	public Wallet getBywalletid(@PathVariable ("walletid") String walletid) {
		return service.getBywalletid(walletid);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Wallet>> getAll(@RequestBody Wallet wallet){
		
		 return new ResponseEntity(service.getAll(wallet),HttpStatus.OK);
	}
	   @PostMapping("/deposit")
	    public ResponseEntity<String> deposit(@RequestParam String id, @RequestParam BigDecimal amount) {
	        // Validate input
	        
	        return new ResponseEntity(service.deposit(id, amount),HttpStatus.OK);
	      //  return ResponseEntity.ok("Money deposited successfully");
	    }
	   @PostMapping("/recharge")
	    public ResponseEntity<String> recharge(@RequestParam String id, @RequestParam BigDecimal amount,@RequestParam Long mobileno) {
	      
		return new ResponseEntity(service.recharge(id, amount,mobileno), HttpStatus.OK);
	    }
	   @PostMapping("/withdraw")
	    public ResponseEntity<String> withdraw(@RequestParam String id, @RequestParam BigDecimal amount) {	        
		 
	    		return new ResponseEntity(service.withdraw(id, amount), HttpStatus.OK);
         }
	
	    }
	   


