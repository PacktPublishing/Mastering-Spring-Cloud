package pl.piomin.services.customer.contract;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pl.piomin.services.customer.model.Customer;

public interface CustomerService {

	@PostMapping
	Customer add(@RequestBody Customer customer);
	
	@PutMapping
	Customer update(@RequestBody Customer customer);
	
	@GetMapping("/{id}")
	Customer findById(@PathVariable("id") Long id);
	
	@GetMapping("/withAccounts/{id}")
	Customer findByIdWithAccounts(@PathVariable("id") Long id);
	
	@PostMapping("/ids")
	List<Customer> find(@RequestBody List<Long> ids);
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") Long id);
	
}
