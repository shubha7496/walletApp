package com.wallet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wallet.entity.Transaction;


@Repository
public interface TransactionRepo extends MongoRepository<Transaction, String> {
	List<Transaction>findBytransactionid(String transactionid);
}
