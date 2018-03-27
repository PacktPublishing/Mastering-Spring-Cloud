package pl.piomin.services.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping
	public Customer add(@RequestBody Customer customer) {
		return service.add(customer);
	}

	@PutMapping
	public Customer update(@RequestBody Customer customer) {
		return service.update(customer);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@GetMapping("/withAccounts/{id}")
	public Customer findByIdWithAccounts(@PathVariable("id") Long id) {
		Customer c = service.findById(id);
		c.setAccounts(service.findCustomerAccounts(id));
		return c;
	}

	@PostMapping("/ids")
	public List<Customer> find(@RequestBody List<Long> ids) {
		return service.find(ids);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
