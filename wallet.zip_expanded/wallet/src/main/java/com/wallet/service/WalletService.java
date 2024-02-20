package com.wallet.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entity.ServiceType;
import com.wallet.entity.Transaction;
import com.wallet.entity.TransactionStatus;
import com.wallet.entity.TransactionType;
import com.wallet.entity.Wallet;
import com.wallet.exception.InsufficientBalanceException;
import com.wallet.exception.UserNotFoundException;
import com.wallet.repository.TransactionRepo;
import com.wallet.repository.WalletRepository;
import com.wallet.response.APIResponse;



@Service
public class WalletService {
	
	@Autowired
	private WalletRepository repository;
	
	@Autowired
	private waletservice waletservice;

	
	
	@Autowired
	private TransactionRepo repo;
	
	 public APIResponse deposit(String walletid, BigDecimal amount) {
	        Optional<Wallet> optionalWallet = repository.findById(walletid);
	        if (optionalWallet.isPresent()) {
	            Wallet wallet = optionalWallet.get();
	            wallet.setBalance(wallet.getBalance().add(amount));
	            repository.save(wallet);
	      
	            Transaction transaction = new Transaction();
	            transaction.setWalletid(walletid);
	            transaction.setAmount(amount);
	            transaction.setTransactionid(walletid);
	            transaction.setTransactiondate(LocalDateTime.now());
	            transaction.setDescription("money deposit success");
	            transaction.setStatus(TransactionStatus.SUCCESS);
	            transaction.setType(TransactionType.WALLET);
	            transaction.setServiceType(ServiceType.WALLET_DEPOSIT);
	            transaction.setReferenceno(UUID.randomUUID().toString());
	            repo.save(transaction);
		        APIResponse  apiResponse= new APIResponse(200,transaction);
		        return apiResponse;
	        } else {
		        APIResponse  apiResponse= new APIResponse(400,TransactionStatus.FAILED);
		        return apiResponse;
	    }
	        
	}

	public Wallet create(Wallet wallet) {
	
		return repository.save(wallet);
	}
		
	public APIResponse<List<Wallet>> getAll(Wallet wallet) {
	 APIResponse  apiResponse= new APIResponse(200,repository.findAll());

	return apiResponse;
	}
	
	public Optional<Wallet> get(String id){
		return repository.findById(id);
	}
	//@Transactional
	public APIResponse recharge(String id, BigDecimal amount,Long mobileno) {
		Transaction wallet1 = new  Transaction();
		 Wallet wallet = repository.findById(id)
	                .orElseThrow(() -> new UserNotFoundException("User not found"));

	        BigDecimal currentBalance = wallet.getBalance();
	        if (currentBalance.compareTo(amount) < 0) {
	            throw new InsufficientBalanceException("Insufficient balance");
	        }

	     
	        BigDecimal updatedBalance = currentBalance.subtract(amount);
	        wallet.setBalance(updatedBalance);
	        wallet.setMobileno(mobileno);
	        wallet1.setAmount(amount);
	        wallet1.setDescription("mobile recharge success");
	        wallet1.setTransactiondate(LocalDateTime.now());
	        wallet1.setUpdatedDate(new Date());
	        wallet1.setWalletid(wallet.getWalletid());
	        wallet1.setStatus(TransactionStatus.SUCCESS);
	        wallet1.setType(TransactionType.WALLET);
	        wallet1.setServiceType(ServiceType.MOBILE_RECHARGE);
	        wallet1.setReferenceno(UUID.randomUUID().toString());
	        wallet1=repo.save(wallet1);
	        repository.save(wallet);
	        APIResponse  apiResponse= new APIResponse(200,wallet1);
	        return  apiResponse;
 
	    }
	
	public APIResponse withdraw(String id, BigDecimal amount) {
		Transaction wallet1 = new  Transaction();
		 Wallet wallet = repository.findById(id)
	                .orElseThrow(() -> new UserNotFoundException("User not found"));

	        BigDecimal currentBalance = wallet.getBalance();
	        if (currentBalance.compareTo(amount) < 0) {
	            throw new InsufficientBalanceException("Insufficient balance");
	        }
	     try {
	        BigDecimal updatedBalance = currentBalance.subtract(amount);
	        wallet.setBalance(updatedBalance);
	        wallet1.setAmount(amount);
	        wallet1.setDescription("money withdraw success");
	        wallet1.setTransactiondate(LocalDateTime.now());
	        wallet1.setWalletid(wallet.getWalletid());
	        wallet1.setStatus(TransactionStatus.SUCCESS);
	        wallet1.setType(TransactionType.WALLET);
	        wallet1.setServiceType(ServiceType.WALLET_WITHDRAW);
	        wallet1.setReferenceno(UUID.randomUUID().toString());
	        wallet1=repo.save(wallet1);
	        repository.save(wallet);
	        APIResponse  apiResponse= new APIResponse(200,wallet1);

            return apiResponse;

	       } catch (InsufficientBalanceException e) {
	         APIResponse apiResponse = new APIResponse();
	         apiResponse.setSuccess(400);
	         apiResponse.setData("Insufficient balance");
	         return apiResponse;
	     } catch (UserNotFoundException e) {
	         APIResponse apiResponse = new APIResponse();
         apiResponse.setSuccess(400);
	         apiResponse.setData("User not found");
	         return apiResponse;
	     }
	    }
		// TODO Auto-generated method stub

	
	public List<Wallet> getwallets() {
		List<Wallet>shoppes=repository.findAll();
		List<Wallet>newShopList=shoppes.stream().map(wallet-> {
			wallet.setTransactions(waletservice.getBywalletid(wallet.getWalletid()));
			return wallet;
		}).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return newShopList;
	}
	public Wallet  getBywalletid(String transactionid) {
		Wallet shop=repository.findById(transactionid).orElseThrow(()-> new RuntimeException("shop not found"));
		// TODO Auto-generated method stub
		shop.setTransactions(waletservice.getBywalletid(shop.getWalletid()));
		return shop;
	}

		
	}
