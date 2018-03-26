package pl.piomin.services.account.model;

public class Account {

	private Long id;
	private String number;
	private int balance;
	private Long customerId;

	public Account() {

	}
	
	public Account(String number, int balance, Long customerId) {
		this.number = number;
		this.balance = balance;
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
