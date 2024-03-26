package com.alabi.app;

public class README {//NAME: ALABI RAZAQ, EMAIL:captain.alabi@gmail.com, PHONE: 08032500469, 09073172216, 08114300655
	/*
		HOW TO RUN THE PROTOTYPE
		The app is a spring boot app with no special configuration except the starter and lombok.
		Open the project folder in your favorite IDE
		Update project to ensure dependencies are ready
		Open the Test.java file, enter your bank network name in the
		right click anywhere in the file, select run as, select java application
		Test for any preselected user will start running and the result of the test would be displayed in console
		Change the users from 1 to 6 to complete test for all the users.		
		
		ASSUMPTIONS MADE
		
		TRANSACTION CATEGORIES
		4 categories of users are created.
		1. users 1 and 2 are in the same category: having Transactions that are 5x 
		above the user's average transaction amount in the last 24 hours.
		2. users 3 and 4 are in the same category: having transactions in more than 
		3 distinct services within a 5-minute window.
		3. user5 is having a sequence of transactions indicating "ping-pong" 
		activity (transactions bouncing back and forth between two services) within 10 minutes
		4. user6 serves as a control for all the tests, having non of the suspicious transactions
		
		REAL TIME
		To simulate a real time scenario, all the transactions generated for each user were merged into
		a single list to act as a central database and each user's list of transactions were fetched
		from there for testing.
		
		FRAUD DETECTION
		
		1. Testing for Transactions that are 5x above the user's average transaction amount in the last 24 hours:
		Assume the suspicious transaction has the greatest amount in the list. find it out among 24hrs
		transactions, minus it from the sum of all the rest, find the average of the rest, multiply it by 5 and compare the result with
		the highest amount. 
		
		2. Testing for a user conducting transactions in more than 3 distinct services within a 5-minute
		window:
		Assume the serviceIDs for distinct transactions is different from all others.
		Fetch such transactions among last 5 minutes transactions and count them
		
		3. Testing for a sequence of transactions indicating "ping-pong" activity (transactions bouncing back and forth
		between two services) within 10 minutes:
		Assume that repeated services indicates repeated serviceIDs. 
		Find out repeated services among last 10 minutes transactions and count them. 
		If greater than or equal to 2, the transaction could be suspicious
		
		*/
}
