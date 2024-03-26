package com.alabi.app.transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alabi.app.amount.AmountsGenerator;
import com.alabi.app.timestamp.TimestampGenerator;

public class User3Or4TransactionGenerator {

	TimestampGenerator timestampGenerator = new TimestampGenerator();
	AmountsGenerator amountsGenerator = new AmountsGenerator();

	/*
	 * generate Transactions For User 3 Or 4: A user conducting transactions in more
	 * than 3 distinct services within a 5-minute window
	 */
	public List<Transaction> generateTransactionsForUser3Or4(String userID) {
		List<Transaction> userTransactions = new ArrayList<>();
		List<String> serviceIDList = Arrays.asList("serviceA", "serviceB", "serviceC", "serviceD");
		List<String> usersIDList = Arrays.asList(userID, userID, userID, userID);
		for (int i = 0; i < usersIDList.size(); i++) {
			if (usersIDList.get(i).equals(userID)) {
				for (int j = 0; j < 4; j++) {
					userTransactions.add(new Transaction(timestampGenerator.generateTimeStampRange(0, 5),
							amountsGenerator.generateOtherAmt(), userID, serviceIDList.get(j)));
				}
			}
		}
		return userTransactions;
	}
}
