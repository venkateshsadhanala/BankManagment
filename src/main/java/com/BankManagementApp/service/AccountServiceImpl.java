package com.BankManagementApp.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.BankManagementApp.dao.Account;
import com.BankManagementApp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo;

	private AccountServiceImpl(AccountRepository repo) {
		this.repo = repo;
	}

	@Override
	public Account createAccount(Account account) {
		Account save = repo.save(account);
		return save;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> byId = repo.findById(accountNumber);
		if (byId.isEmpty()) {
			throw new RuntimeErrorException(null, "Account is not present");
		}
		Account account_found = byId.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> all = repo.findAll();
		return all;
	}

	@Override
	public Account DepositeAmount(Long accountNumber, Double amount) {
		Optional<Account> byId = repo.findById(accountNumber);
		if (byId.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent = byId.get();
		Double totalBalance = accountPresent.getAccount_balance() + amount;
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		return accountPresent;

	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		Optional<Account> byId = repo.findById(accountNumber);
		if (byId.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent = byId.get();
		Double accountBalance = accountPresent.getAccount_balance() - amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		Account accountDetailsByAccountNumber = getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
	}
}
