package pl.piomin.services.order.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String id;
	private String name;
	private CustomerType type;
	private List<Account> accounts = new ArrayList<>();

	public Customer() {

	}
	
	public Customer(String id, String name, CustomerType type, List<Account> accounts) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.accounts = accounts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
