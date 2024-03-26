package com.alabi.app.transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alabi.app.amount.AmountsGenerator;
import com.alabi.app.timestamp.TimestampGenerator;

public class User5TransactionGenerator {

	TimestampGenerator timestampGenerator = new TimestampGenerator();
	AmountsGenerator amountsGenerator = new AmountsGenerator();

	/*
	 * generate transactions for user5 that has a sequence of transactions
	 * indicating "ping-pong" activity (transactions bouncing back and forth between
	 * two services) within 10 minutes.
	 */
	public List<Transaction> generateTransactionsForUser5(String userID) {
		List<String> allUserIDsList = Arrays.asList("user1", "user2", "user3", "user4", "user5", "user6");
		List<Transaction> userTransactions = new ArrayList<>();
		List<String> serviceIDList = Arrays.asList("serviceA", "serviceB", "serviceA", "serviceB");//ping-pong
		List<String> usersIDList = allUserIDsList;
		for (int i = 0; i < usersIDList.size(); i++) {
			if (usersIDList.get(i).equals("user5")) {
				for (int j = 0; j < 4; j++) {
					userTransactions.add(new Transaction(timestampGenerator.generateTimeStampRange(6, 9),
							amountsGenerator.generateOtherAmt(), "user5", serviceIDList.get(j)));
				}
			}
		}
		return userTransactions;
	}
}
