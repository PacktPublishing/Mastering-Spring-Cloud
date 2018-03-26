package pl.piomin.services.account.contract;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pl.piomin.services.account.model.Account;

public interface AccountService {

	@PostMapping
	Account add(@RequestBody Account account);
	
	@PutMapping
	Account update(@RequestBody Account account);
	
	@PutMapping("/withdraw/{id}/{amount}")
	Account withdraw(@PathVariable("id") Long id, @PathVariable("amount") int amount);
	
	@GetMapping("/{id}")
	Account findById(@PathVariable("id") Long id);
	
	@GetMapping("/customer/{customerId}")
	List<Account> findByCustomerId(@PathVariable("customerId") Long customerId);
	
	@PostMapping("/ids")
	List<Account> find(@RequestBody List<Long> ids);
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") Long id);
	
}
