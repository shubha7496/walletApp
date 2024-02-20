package com.wallet.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Wallet")
public class Wallet {
	
	@Id
	private String walletid;
	
	private BigDecimal balance;
	
	private Long mobileno;
	
	transient private List<Transaction>transactions;

	
//	@DBRef
//	private List<Transaction> transactions;
	
	

}
