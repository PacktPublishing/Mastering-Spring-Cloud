package pl.piomin.services.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.account.model.Account;
import pl.piomin.services.customer.client.AccountClient;
import pl.piomin.services.customer.contract.CustomerService;
import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.repository.CustomerRepository;

@RestController
public class CustomerController implements CustomerService {

	@Autowired
	AccountClient accountClient;
	@Autowired
	CustomerRepository repository;
	
	public Customer add(@RequestBody Customer customer) {
		return repository.add(customer);
	}
	
	public Customer update(@RequestBody Customer customer) {
		return repository.update(customer);
	}
	
	public Customer findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	public Customer findByIdWithAccounts(@PathVariable("id") Long id) {
		List<Account> accounts = accountClient.findByCustomerId(id);
		Customer c = repository.findById(id);
		c.setAccounts(accounts);
		return c;
	}
	
	public List<Customer> find(@RequestBody List<Long> ids) {
		return repository.find(ids);
	}
	
	public void delete(@PathVariable("id") Long id) {
		repository.delete(id);
	}
	
}
