package pl.piomin.services.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.account.contract.AccountService;
import pl.piomin.services.account.model.Account;
import pl.piomin.services.account.repository.AccountRepository;

@RestController
public class AccountController implements AccountService {

	@Autowired
	AccountRepository repository;

	public Account add(@RequestBody Account account) {
		return repository.add(account);
	}

	public Account update(@RequestBody Account account) {
		return repository.update(account);
	}

	public Account withdraw(@PathVariable("id") Long id, @PathVariable("amount") int amount) {
		Account account = repository.findById(id);
		account.setBalance(account.getBalance() - amount);
		return repository.update(account);
	}

	public Account findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	public List<Account> findByCustomerId(@PathVariable("customerId") Long customerId) {
		return repository.findByCustomer(customerId);
	}

	public List<Account> find(@RequestBody List<Long> ids) {
		return repository.find(ids);
	}

	public void delete(@PathVariable("id") Long id) {
		repository.delete(id);
	}

}
