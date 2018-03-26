package pl.piomin.services.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import pl.piomin.services.order.repository.OrderRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(OrderApplication.class).web(true).run(args);
	}
	
	@Bean
	OrderRepository repository() {
		return new OrderRepository();
	}

}
