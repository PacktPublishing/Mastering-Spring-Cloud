package pl.piomin.services.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.product.contract.ProductService;
import pl.piomin.services.product.model.Product;
import pl.piomin.services.product.repository.ProductRepository;

@RestController
public class ProductController implements ProductService {

	@Autowired
	ProductRepository repository;
	
	public Product add(@RequestBody Product product) {
		return repository.add(product);
	}
	
	public Product update(@RequestBody Product product) {
		return repository.update(product);
	}
	
	public Product findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	public List<Product> find(@RequestBody List<Long> ids) {
		return repository.find(ids);
	}
	
	public void delete(@PathVariable("id") Long id) {
		repository.delete(id);
	}
	
}
