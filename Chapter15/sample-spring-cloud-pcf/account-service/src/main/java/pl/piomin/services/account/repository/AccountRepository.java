package pl.piomin.services.account.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.piomin.services.account.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

	List<Account> findByIdIn(List<String> ids);
	List<Account> findByCustomerId(String customerId);
	
}
