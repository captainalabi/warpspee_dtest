package com.alabi.app.amount;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.alabi.app.transaction.Transaction;

@Service
public class AmountsGenerator {

	/*
	 * returns amounts not more than 2000
	 */
	public double amountsNotMoreThan5K() {
		Random random = new Random();
		return Math.floor(1 + random.nextDouble() * 1999);
	}

	/*
	 * generate amount for other transactions: less between 1,500 and 2,000
	 */
	public double generateOtherAmt() {
		Random random = new Random();
		return Math.floor(1500 + (2000 - 1500) * random.nextDouble());
	}

	// method to find average amount of a transaction list
	public double findAverageAmount(List<Transaction> arrayList) {
		double greatestAmount = findGreatestAmount(arrayList);
		//find average amount
				double sum = 0;
				for(Transaction transaction : arrayList) {
					sum += transaction.getAmount();
				}
				double average = Math.floor((sum - greatestAmount) / (arrayList.size() - 1));
		return average;
	}

	// method to find greatest amount
	public double findGreatestAmount(List<Transaction> arrayList) {
		//find greatest amount
				double greatestAmount = arrayList.get(0).getAmount();//assuming first is greatest
				//System.out.println("greatestAmount ::::::::::::: " + greatestAmount);
				for(Transaction transaction : arrayList) {
					if(transaction.getAmount() > greatestAmount) {
						greatestAmount = transaction.getAmount();//change to greater value if true
					}
				}
		return greatestAmount;
	}
}
