package com.transaction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.transaction.entity.Transaction;
import com.transaction.repository.TransactionRepo;
import com.transaction.response.APIResponse;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	public Transaction create(Transaction transaction) {
		return transactionRepo.save(transaction);
	}
	
	
	public APIResponse<List<Transaction>> getAll(Transaction transaction) {
		 APIResponse  apiResponse= new APIResponse(200,transactionRepo.findAll());

		return apiResponse;
	}

	
	public List<Transaction>gettrasaction(String transactionid){
		
		// APIResponse  apiResponse= new APIResponse(200,transactionRepo.findById(transactionid));

		return transactionRepo.findBytransactionid(transactionid);
	}


	public Optional<Transaction> get(String id) {
		// TODO Auto-generated method stub
		return transactionRepo.findById(id);
	}


	public List<Transaction> getTransactionBywalleid(String walletid) {
		// TODO Auto-generated method stub
		return transactionRepo.findBywalletid(walletid);
	}
	
	

}
