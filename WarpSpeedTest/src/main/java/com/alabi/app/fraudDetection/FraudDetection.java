package com.alabi.app.fraudDetection;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alabi.app.amount.AmountsGenerator;
import com.alabi.app.timestamp.TimestampGenerator;
import com.alabi.app.transaction.Transaction;
import com.alabi.app.transaction.User1Or2TransactionGenerator;

@Service
public class FraudDetection {

	TimestampGenerator timestampGenerator = new TimestampGenerator();
	AmountsGenerator amountsGenerator = new AmountsGenerator();
	static User1Or2TransactionGenerator user1Or2TransactionGenerator = new User1Or2TransactionGenerator();

	/*
	 * method to check if user has amount fiveXAbove average
	 */
	public boolean detect5XAboveAverage(List<Transaction> userTransactionList) {
		// find greatest amount
		double greatestAmount = user1Or2TransactionGenerator.findGreatestAmount(userTransactionList);
		//find average of all transactions
		double average = user1Or2TransactionGenerator.findAverageAmount(userTransactionList);
		boolean fiveXGreater = greatestAmount >= average * 5;
		String userID = userTransactionList.get(0).getUserID();
		if (fiveXGreater) {
			System.out.println("Fraud Alert!\n" + userID + " has transaction above 5X of Average.");
		} else {
			System.out.println(userID + " does not have transaction above 5X of Average.");
		}
		return fiveXGreater;
	}

	/*
	 * 
	 * @Param(String serviceID)
	 */
	public boolean is5MinutesWindow(List<Transaction> userTransactionList) {
		String userID = userTransactionList.get(1).getUserID();
		long fiveMin = 5 * 60 * 1000;
		long currentTime = System.currentTimeMillis();
		long fiveMinwindow = currentTime - fiveMin;
		// count number of transactions later than 5 minutes ago
		long count = userTransactionList.stream().filter(transaction -> transaction.getTimestamp() > fiveMinwindow)
				.count();
		boolean is5MinutesWindow = count >= 3;
		if (is5MinutesWindow) {
			// send message
			System.out.println("Fraud Alert!\n3 rapid transactions suspected for user: " + userID);
		} else {
			System.out.println("No 3 rapid transactions suspected for user: " + userID);
		}
		return is5MinutesWindow;
	}

	/*
	 * checks if at least there are 3 distinct serviceIDs returns true if yes -
	 * Tested else returns false
	 * 
	 * @Param(String serviceID)
	 */
	long theCount = 0;

	public boolean isServiceIDs3Distinct(List<Transaction> userTransactionList) {
		String userID = userTransactionList.get(1).getUserID();
		Map<String, Long> serviceCount = userTransactionList.stream()
				.collect(Collectors.groupingBy(Transaction::getServiceID, Collectors.counting()));
		serviceCount.forEach((serviceID, count) -> {
			theCount = count;
		});
		boolean isDistinct = theCount >= 3;
		// String message = preparedMessage();
		if (isDistinct) {
			String message = "Fraud Alert!\n" + userID + " has transactions with 3 distinct services";
			System.out.println(message);
		} else {
			System.out.println(userID + "  has NO transactions with 3 distinct services");
		}
		return isDistinct;
	}

	/*
	 * detect ping-pong activity: A user that has a sequence of transactions
	 * indicating "ping-pong" activity (transactions bouncing back and forth between
	 * two services) within 10 minutes.
	 */
	public boolean isPingPongActivity(List<Transaction> transactions) {
		List<String> serviceIDList =
				// check ping-pong activity
				transactions
				.stream()
				.map(Transaction::getServiceID)
				.distinct()
				.collect(Collectors.toList());
		boolean isPingPong = serviceIDList.size() == 2;
		// check 10 minutes window
		Random random = new Random();
		long tenMinAgo = System.currentTimeMillis() - random.nextInt(10 * 60 * 1000);
		List<Long> timestampDList = transactions.stream().filter(transaction -> transaction.getTimestamp() > tenMinAgo)
				.map(Transaction::getTimestamp).distinct().collect(Collectors.toList());
		String userID = transactions.get(1).getUserID();
		boolean is10MinutesWindow = timestampDList.size() >= 2;
		boolean isPingPongActivity = isPingPong && is10MinutesWindow;
		if (isPingPongActivity) {
			System.out.println("Fraud Alert\n" + userID + " is engaged in a Ping-pong activity");
		} else {
			System.out.println(userID + " is NOT engaged in a Ping-pong activity");
		}
		return isPingPongActivity;
	}

}
