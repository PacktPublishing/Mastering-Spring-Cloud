package pl.piomin.services.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.piomin.services.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	List<Product> findByIdIn(List<String> ids);
	
}
