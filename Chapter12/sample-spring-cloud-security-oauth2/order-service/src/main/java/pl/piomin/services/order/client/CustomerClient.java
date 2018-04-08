package pl.piomin.services.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.piomin.services.order.model.Customer;

@FeignClient(name = "customer-service")
public interface CustomerClient {

	@GetMapping("/withAccounts/{customerId}")
	Customer findByIdWithAccounts(@PathVariable("customerId") Long customerId);
	
}
