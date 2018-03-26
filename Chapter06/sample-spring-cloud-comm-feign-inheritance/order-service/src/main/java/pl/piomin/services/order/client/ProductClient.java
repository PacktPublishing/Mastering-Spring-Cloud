package pl.piomin.services.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import pl.piomin.services.product.contract.ProductService;

@FeignClient(name = "product-service")
public interface ProductClient extends ProductService {
	
}
