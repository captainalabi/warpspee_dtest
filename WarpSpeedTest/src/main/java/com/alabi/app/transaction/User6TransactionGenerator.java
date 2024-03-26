package com.alabi.app.transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alabi.app.amount.AmountsGenerator;
import com.alabi.app.timestamp.TimestampGenerator;

public class User6TransactionGenerator {

	TimestampGenerator timestampGenerator = new TimestampGenerator();
	AmountsGenerator amountsGenerator = new AmountsGenerator();

	/*
	 * generate transactions for user5 A non suspicious user to serve as control
	 */
	public List<Transaction> generateTransactionsForUser6(String userID) {
		List<String> allUserIDsList = Arrays.asList("user1", "user2", "user3", "user4", "user6");
		List<Transaction> userTransactions = new ArrayList<>();
		List<String> serviceIDList = Arrays.asList("serviceA", "serviceB", "serviceC", "serviceD");
		List<String> usersIDList = allUserIDsList;
		serviceIDList.set(2, "serviceA");// repeat serviceA
		serviceIDList.set(3, "serviceB");// repeat serviceB
		for (int i = 0; i < usersIDList.size(); i++) {
			if (usersIDList.get(i).equals("user6")) {
				for (int j = 0; j < 4; j++) {
					userTransactions.add(new Transaction(timestampGenerator.generateTimeStampRange(0, 60 * 24),
							amountsGenerator.generateOtherAmt(), "user6", serviceIDList.get(j)));
				}
			}
		}
		return userTransactions;
	}
}
