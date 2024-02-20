package com.wallet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wallet.entity.Wallet;


@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {

}
