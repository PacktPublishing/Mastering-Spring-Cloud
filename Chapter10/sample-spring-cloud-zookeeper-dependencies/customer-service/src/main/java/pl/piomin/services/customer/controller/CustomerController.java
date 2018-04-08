package pl.piomin.services.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.piomin.services.customer.client.AccountClient;
import pl.piomin.services.customer.model.Account;
import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.repository.CustomerRepository;

@RestController
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	AccountClient accountClient;
	@Autowired
	CustomerRepository repository;
	
	@PostMapping
	public Customer add(@RequestBody Customer customer) {
		return repository.add(customer);
	}
	
	@PutMapping
	public Customer update(@RequestBody Customer customer) {
		return repository.update(customer);
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	@GetMapping("/withAccounts/{id}")
	public Customer findByIdWithAccounts(@PathVariable("id") Long id) throws JsonProcessingException {
		List<Account> accounts = accountClient.findByCustomer(id);
		LOGGER.info("Accounts found: {}", mapper.writeValueAsString(accounts));
		Customer c = repository.findById(id);
		c.setAccounts(accounts);
		return c;
	}
	
	@PostMapping("/ids")
	public List<Customer> find(@RequestBody List<Long> ids) {
		return repository.find(ids);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		repository.delete(id);
	}
	
}
