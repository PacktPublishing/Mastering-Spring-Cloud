package pl.piomin.services.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.product.model.Product;
import pl.piomin.services.product.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository repository;
	
	@PostMapping
	public Product add(@RequestBody Product product) {
		return repository.add(product);
	}
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return repository.update(product);
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/ids")
	public List<Product> find(@RequestBody List<Long> ids) {
		return repository.find(ids);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		repository.delete(id);
	}
	
}
