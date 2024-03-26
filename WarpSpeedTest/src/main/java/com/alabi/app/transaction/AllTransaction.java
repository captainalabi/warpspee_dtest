package com.alabi.app.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alabi.app.amount.AmountsGenerator;
import com.alabi.app.timestamp.TimestampGenerator;

/*
 * Adds all the generated transactions to one list so each category can be fetched from there
 * to simulate a real time situation
 * .............................................................................
 */

@Service
public class AllTransaction {

	 TimestampGenerator timestampGenerator = new TimestampGenerator();
	 AmountsGenerator amountsGenerator = new AmountsGenerator();
	 User1Or2TransactionGenerator user1Or2TransactionGenerator = new User1Or2TransactionGenerator();
	 User3Or4TransactionGenerator user3Or4TransactionGenerator = new User3Or4TransactionGenerator();
	 User5TransactionGenerator user5TransactionGenerator = new User5TransactionGenerator();
	 User6TransactionGenerator user6TransactionGenerator = new User6TransactionGenerator();

	public List<Transaction> getAllTransactions() {
		List<Transaction> allTransactions = new ArrayList<>();
		// add user1
		List<Transaction> user1Transactions = user1Or2TransactionGenerator.generateUser1Or2Transactions("user1");
		allTransactions.addAll(user1Transactions);
		// add user2
		List<Transaction> user2Transactions = user1Or2TransactionGenerator.generateUser1Or2Transactions("user2");
		allTransactions.addAll(user2Transactions);
		// add user3
		List<Transaction> user3Transactions = user3Or4TransactionGenerator.generateTransactionsForUser3Or4("user3");
		allTransactions.addAll(user3Transactions);
		// add user4
		List<Transaction> user4Transactions = user3Or4TransactionGenerator.generateTransactionsForUser3Or4("user4");
		allTransactions.addAll(user4Transactions);
		// add user5
		List<Transaction> user5Transactions = user5TransactionGenerator.generateTransactionsForUser5("user5");
		allTransactions.addAll(user5Transactions);
		// add user6
		List<Transaction> user6Transactions = user6TransactionGenerator.generateTransactionsForUser6("user6");
		allTransactions.addAll(user6Transactions);
		return allTransactions;
	}

	public List<Transaction> getAUserTransactions(String userID) {
		return getAllTransactions().stream().filter(transaction -> transaction.getUserID() == userID)
				.collect(Collectors.toList());
	}
}
