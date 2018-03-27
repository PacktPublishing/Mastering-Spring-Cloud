package pl.piomin.services.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {

	@Id
	private String id;
	private String number;
	private int balance;
	private String customerId;

	public Account() {

	}

	public Account(String number, int balance, String customerId) {
		this.number = number;
		this.balance = balance;
		this.customerId = customerId;
	}

	public Account(String id, String number, int balance, String customerId) {
		this.id = id;
		this.number = number;
		this.balance = balance;
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", balance=" + balance + "]";
	}

}
