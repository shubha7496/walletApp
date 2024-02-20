package com.transaction.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.transaction.entity.Transaction;
import com.transaction.service.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@PostMapping("/create")
	public Transaction create(@RequestBody Transaction transaction) {
		return service.create(transaction);
	}
	
	@GetMapping("/{id}")
	public Optional<Transaction> get(@PathVariable ("id") String id) {
		return service.get(id);
	}
	@GetMapping("/transaction")
	public ResponseEntity<List<Transaction>> getAll(@RequestBody Transaction transaction){
		
		 return new ResponseEntity(service.getAll(transaction),HttpStatus.OK);
	}
	@GetMapping("/transaction/{id}")
	public ResponseEntity<List<Transaction>> gettrasaction(@PathVariable ("id") String transactionid) {
		
		return new ResponseEntity(service.gettrasaction(transactionid),HttpStatus.OK);
	}
	@GetMapping("/wallet/{walletid}")
	public ResponseEntity<List<Transaction>> getBywalletid(@PathVariable  String walletid){
		return new  ResponseEntity(service.getTransactionBywalleid(walletid),HttpStatus.OK);
	}
	
//	@GetMapping("/transaction")
//	public List<Transaction> getAll(@RequestBody Transaction transaction){
//		 return service.getAll(transaction);
//	}

}
