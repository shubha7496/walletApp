package com.wallet.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
	
	
	private String transactionid;
	
	private TransactionType type;
	
    private TransactionStatus status;

    private ServiceType serviceType;

	 private String Referenceno;
	private String walletid;
	
	private LocalDateTime transactiondate;
	
	private Date updatedDate;
	

	private BigDecimal amount;
	
	private String description;
	
//	@DBRef
//	private Wallet wallet;


	

	

}
