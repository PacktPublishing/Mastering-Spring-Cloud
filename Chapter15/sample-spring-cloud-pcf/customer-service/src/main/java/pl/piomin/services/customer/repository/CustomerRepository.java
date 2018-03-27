package pl.piomin.services.customer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.piomin.services.customer.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public List<Customer> findByIdIn(List<String> ids);
	
}
