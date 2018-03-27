package pl.piomin.services.customer.model;

public class Account {

	private Long id;
	private String number;
	private int balance;

	public Account() {

	}
	
	public Account(Long id, String number, int balance) {
		this.id = id;
		this.number = number;
		this.balance = balance;
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", balance=" + balance + "]";
	}

}
