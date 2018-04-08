package pl.piomin.services.customer.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Long id;
	private String name;
	private CustomerType type;
	private List<Account> accounts = new ArrayList<>();

	public Customer() {

	}
	
	public Customer(String name, CustomerType type) {
		this.name = name;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
