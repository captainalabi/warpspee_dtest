package com.alabi.app.transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.alabi.app.timestamp.TimestampGenerator;

public class User1Or2TransactionGenerator {

	TimestampGenerator timestampGenerator = new TimestampGenerator();

	/*
	 * method to generate 4 transactions for either user 1 or 2, last of which has
	 * amount = average * 5
	 */
	public List<Transaction> generateUser1Or2Transactions(String userID) {
		Random random = new Random();
		double normal = Math.floor(1500 + (2000 - 1500) * random.nextDouble());
		double greatest = (normal * 5) + normal;// add normal to make it greater(due to random safety factor)

		// store 4 amounts in arrayList
		List<Double> amountsList = Arrays.asList(new Double[] { Math.floor(1500 + (2000 - 1500) * random.nextDouble()),
				Math.floor(1500 + (2000 - 1500) * random.nextDouble()),
				Math.floor(1500 + (2000 - 1500) * random.nextDouble()), greatest });

		// generate an arrayList of 4 transactions and use amountsList to fill the
		// transactions amounts
		// initialize a list of varying services
		List<String> serviceIDs = Arrays.asList(new String[] { "serviceA", "serviceC", "serviceA", "serviceD" });
		// initialize a list of timeStamps between 24hrs
		List<Long> timestamps = Arrays.asList(new Long[] {
				timestampGenerator.generateTimeStampRange(0, 60 * 24),//within last 24 hrs
				timestampGenerator.generateTimeStampRange(0, 60 * 24),
				timestampGenerator.generateTimeStampRange(0, 60 * 24),
				timestampGenerator.generateTimeStampRange(0, 60 * 24)
				});
		List<Transaction> transactionList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			transactionList.add(new Transaction(timestamps.get(i), amountsList.get(i), userID, serviceIDs.get(i)));
		}
		return transactionList;
	}

	// method to find average amount of a transaction list
	public double findAverageAmount(List<Transaction> arrayList) {
		double greatestAmount = findGreatestAmount(arrayList);
		double sum = 0;
		for (Transaction transaction : arrayList) {
			sum += transaction.getAmount();
		}
		double average = Math.floor((sum - greatestAmount) / (arrayList.size() - 1));
		return average;
	}

	// method to find greatest amount
	public double findGreatestAmount(List<Transaction> arrayList) {
		double greatestAmount = arrayList.get(0).getAmount();
		for (Transaction transaction : arrayList) {
			if (transaction.getAmount() > greatestAmount) {
				greatestAmount = transaction.getAmount();
			}
		}
		return greatestAmount;
	}

	// method to find out if greatestAmount is 5x > averageAmount
	public boolean isGreatestAmount5xAverageAmount(String userID, List<Transaction> userTransactionList) {
		double userGreatestAmount = findGreatestAmount(userTransactionList);
		double averageAmount = findAverageAmount(userTransactionList);
		boolean fiveXGreater = userGreatestAmount >= averageAmount * 5;
		return fiveXGreater;
	}
}
