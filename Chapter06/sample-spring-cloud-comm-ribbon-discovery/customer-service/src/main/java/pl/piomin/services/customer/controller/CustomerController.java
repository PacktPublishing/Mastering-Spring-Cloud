package pl.piomin.services.customer.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.piomin.services.customer.model.Account;
import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	RestTemplate template;
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
	public Customer findByIdWithAccounts(@PathVariable("id") Long id) {
		Account[] accounts = template.getForObject("http://account-service/customer/{customerId}", Account[].class, id);
		Customer c = repository.findById(id);
		c.setAccounts(Arrays.stream(accounts).collect(Collectors.toList()));
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
