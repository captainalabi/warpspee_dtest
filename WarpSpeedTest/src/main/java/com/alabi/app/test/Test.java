package com.alabi.app.test;

import java.util.List;

import com.alabi.app.fraudDetection.FraudDetection;
import com.alabi.app.networkConnection.NetworkLatencyChecker;
import com.alabi.app.transaction.AllTransaction;
import com.alabi.app.transaction.Transaction;
import com.alabi.app.transaction.User1Or2TransactionGenerator;
import com.alabi.app.transaction.User3Or4TransactionGenerator;
import com.alabi.app.transaction.User5TransactionGenerator;
import com.alabi.app.transaction.User6TransactionGenerator;

public class Test {

	static AllTransaction allTransaction = new AllTransaction();
	static FraudDetection fraudDetection = new FraudDetection();
	static User1Or2TransactionGenerator user1Or2TransactionGenerator = new User1Or2TransactionGenerator();
	static User3Or4TransactionGenerator user3Or4TransactionGenerator = new User3Or4TransactionGenerator();
	static User5TransactionGenerator user5TransactionGenerator = new User5TransactionGenerator();
	static User6TransactionGenerator user6TransactionGenerator = new User6TransactionGenerator();
	static NetworkLatencyChecker networkLatencyChecker = new NetworkLatencyChecker();
	
	public static void testDetect5XAboveAverage() {
		//List<Transaction> userTransactionList = transactionGenerator.generateUser1Or2Transactions("user1");
		//List<Transaction> userTransactionList = transactionGenerator.generateUser1Or2Transactions("user2");
		//List<Transaction> userTransactionList = transactionGenerator.generateTransactionsForUser3Or4("user3");
		//List<Transaction> userTransactionList = transactionGenerator.generateTransactionsForUser3Or4("user4");
		List<Transaction> userTransactionList = user6TransactionGenerator.generateTransactionsForUser6("user6");
		
		fraudDetection.detect5XAboveAverage(userTransactionList);
	}
	
	public static void test5MinutesWindowAndDistinct(List<Transaction> userTransactionList) {
		//List<Transaction> userTransactionList = user1Or2TransactionGenerator.generateUser1Or2Transactions("user1");
				//List<Transaction> userTransactionList = transactionGenerator.generateUser1Or2Transactions("user2");
				//List<Transaction> userTransactionList = transactionGenerator.generateTransactionsForUser3Or4("user3");
				//List<Transaction> userTransactionList = transactionGenerator.generateTransactionsForUser3Or4("user4");
		boolean isDistinct = fraudDetection.isServiceIDs3Distinct(userTransactionList);
		boolean is5MinutesWindow = fraudDetection.is5MinutesWindow(userTransactionList);
		String userID = userTransactionList.get(1).getUserID();
		if(isDistinct && is5MinutesWindow) {
			System.out.println("Fraud Alert!\n " + userID +
					" Has 3 distinct transactions within 5 minutes window.");
		}else {
			System.out.println( userID +
					" Has NO distinct transactions within 5 minutes window.");
		}
	}
	
	public void testPingPongActivity() {
		List<Transaction> user6List = user3Or4TransactionGenerator.generateTransactionsForUser3Or4("user3");
		fraudDetection.isPingPongActivity(user6List);
	}
	
	public void testNetworkLatency() {
		networkLatencyChecker.isNetworkLatent("www.google.com");
	}
	
	/* Combines all the tests and raises alarm if any of them is suspected 
	*/
	public static void fraudTest(List<Transaction> userTransactions) {
		       if( fraudDetection.detect5XAboveAverage(userTransactions)
				|| fraudDetection.isServiceIDs3Distinct(userTransactions)
				|| fraudDetection.is5MinutesWindow(userTransactions)				
				|| fraudDetection.isPingPongActivity(userTransactions)		    		   
				) {
			System.out.println("Fraud suspected!");
			System.out.println("May be due to network");
		}else {
			System.out.println("No Fraud suspected!");
		}
	}
	
	/* Combines all the tests and raises alarm if any of them is suspected 
	 * considering network latency
	*/
	public static void fraudTest(List<Transaction> userTransactions, String networkName) {
		       if( (fraudDetection.detect5XAboveAverage(userTransactions)
				|| fraudDetection.isServiceIDs3Distinct(userTransactions)
				|| fraudDetection.is5MinutesWindow(userTransactions)				
				|| fraudDetection.isPingPongActivity(userTransactions))
		    		   || networkLatencyChecker.isNetworkLatent(networkName)
				) {
			System.out.println("Fraud suspected!");
			System.out.println("May be due to network latency");
		}else {
			System.out.println("No Fraud suspected!");
		}
	}
	
	/*Testing ping pong*/
	//copy to main class
		public static void main(String[] args) {
			
			//Overal test
			List<Transaction> user1TransactionList = allTransaction.getAUserTransactions("user1");
			List<Transaction> user2TransactionList = allTransaction.getAUserTransactions("user2");
			List<Transaction> user3TransactionList = allTransaction.getAUserTransactions("user3");
			List<Transaction> user4TransactionList = allTransaction.getAUserTransactions("user4");
			List<Transaction> user5TransactionList = allTransaction.getAUserTransactions("user5");
			List<Transaction> user6TransactionList = allTransaction.getAUserTransactions("user6");
			fraudTest(user3TransactionList, "www.google.com");
			//you may remove the network name to test without considering network latency
			//fraudTest(user6TransactionList);
			
		}
}
