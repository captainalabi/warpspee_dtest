package com.alabi.app.transaction;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
A transaction class to match the key-value pairs of the data input
implement encapsulation to restrict data access, provide constructors, 
getters setters and mark as entity for database storage of data
*/

@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	private Integer id;
	@JsonProperty("timestamp")
	private Long timestamp;
	@JsonProperty("amount")
	private double amount;
	@JsonProperty("userID")
	private String userID;
	@JsonProperty("serviceID")
	private String serviceID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public Transaction(Long timestamp, double amount, String userID, String serviceID) {
		super();
		this.timestamp = timestamp;
		this.amount = amount;
		this.userID = userID;
		this.serviceID = serviceID;
	}
	
	  @Override public String toString() { return "Transaction [timestamp=" +
	  timestamp + ", amount=" + amount + ", userID=" + userID + ", serviceID=" +
	  serviceID + "]"; }
	 /* 
	 * @Override public int hashCode() { return Objects.hash(amount, serviceID,
	 * timestamp, userID); }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; Transaction other = (Transaction) obj; return
	 * Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) &&
	 * Objects.equals(serviceID, other.serviceID) && Objects.equals(timestamp,
	 * other.timestamp) && Objects.equals(userID, other.userID); }
	 */
}
