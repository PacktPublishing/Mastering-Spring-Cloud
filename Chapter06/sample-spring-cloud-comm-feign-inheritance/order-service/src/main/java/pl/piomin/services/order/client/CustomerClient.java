package pl.piomin.services.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import pl.piomin.services.customer.contract.CustomerService;

@FeignClient(name = "customer-service")
public interface CustomerClient extends CustomerService {

	
}
