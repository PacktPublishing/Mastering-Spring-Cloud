package pl.piomin.services.customer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.model.CustomerType;
import pl.piomin.services.customer.repository.CustomerRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(CustomerApplication.class).web(true).run(args);
	}

	@Bean
	CustomerRepository repository() {
		CustomerRepository repository = new CustomerRepository();
		repository.add(new Customer("John Scott", CustomerType.NEW));
		repository.add(new Customer("Adam Smith", CustomerType.REGULAR));
		repository.add(new Customer("Jacob Ryan", CustomerType.VIP));
		return repository;
	}
	
}
