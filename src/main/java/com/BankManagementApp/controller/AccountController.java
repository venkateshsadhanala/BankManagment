	package com.BankManagementApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankManagementApp.dao.Account;
import com.BankManagementApp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;

	private AccountController(AccountService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account createAccount = service.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
	}

	@GetMapping("/getting{id}")
	public Account getAccountByAccountNumber(@PathVariable Long accountNumber) {
		Account account = service.getAccountDetailsByAccountNumber(accountNumber);
		return account;
	}

	@GetMapping("/getall{id}")
	public List<Account> getAllAccountDetails() {
		List<Account> allAccountDetails = service.getAllAccountDetails();
		return allAccountDetails;
	}

	@PutMapping("/deposite/{accountNumber}/{amount}")
	public Account DepositeAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
		Account depositeAmount = service.DepositeAmount(accountNumber, amount);
		return depositeAmount;
	}

	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public Account withdrawAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
		Account withdrawAmount = service.withdrawAmount(accountNumber, amount);
		return withdrawAmount;
	}

	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
		service.closeAccount(accountNumber);
		return ResponseEntity.ok("Account Closed");

	}
}
