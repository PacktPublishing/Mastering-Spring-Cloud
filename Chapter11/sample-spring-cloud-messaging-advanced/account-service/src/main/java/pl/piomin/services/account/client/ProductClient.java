package pl.piomin.services.account.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pl.piomin.services.account.model.Product;

@FeignClient(name = "product-service")
public interface ProductClient {

	@PostMapping("/ids")
	List<Product> findByIds(@RequestBody List<Long> ids);
	
}
