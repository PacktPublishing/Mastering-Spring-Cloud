package pl.piomin.services.product.contract;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pl.piomin.services.product.model.Product;

public interface ProductService {

	@PostMapping
	Product add(@RequestBody Product product);
	
	@PutMapping
	Product update(@RequestBody Product product);
	
	@GetMapping("/{id}")
	Product findById(@PathVariable("id") Long id);
	
	@PostMapping("/ids")
	List<Product> find(@RequestBody List<Long> ids);
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") Long id);
	
}
