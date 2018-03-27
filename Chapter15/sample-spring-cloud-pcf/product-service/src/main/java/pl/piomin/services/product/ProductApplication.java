package pl.piomin.services.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ProductApplication.class).web(true).run(args);
	}
	
}
