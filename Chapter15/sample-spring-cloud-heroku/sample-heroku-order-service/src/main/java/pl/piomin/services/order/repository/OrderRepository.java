package pl.piomin.services.order.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.piomin.services.order.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	
	List<Order> findByIdIn(List<String> ids);
	int countByCustomerId(String customerId);
	
}
