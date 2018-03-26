package pl.piomin.services.customer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.model.CustomerType;
import pl.piomin.services.customer.repository.CustomerRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerApplication {
	
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
