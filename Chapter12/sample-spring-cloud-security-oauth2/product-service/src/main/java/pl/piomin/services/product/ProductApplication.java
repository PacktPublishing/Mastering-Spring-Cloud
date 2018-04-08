package pl.piomin.services.product;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

import pl.piomin.services.product.model.Product;
import pl.piomin.services.product.repository.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ProductApplication.class).web(true).run(args);
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    loggingFilter.setMaxPayloadLength(1000);
	    loggingFilter.setAfterMessagePrefix("REQ:");
	    return loggingFilter;
	}
	
	@Bean
	ProductRepository repository() {
		ProductRepository repository = new ProductRepository();
		repository.add(new Product("Test1", 1000));
		repository.add(new Product("Test2", 1500));
		repository.add(new Product("Test3", 2000));
		repository.add(new Product("Test4", 3000));
		repository.add(new Product("Test5", 1300));
		repository.add(new Product("Test6", 2700));
		repository.add(new Product("Test7", 3500));
		repository.add(new Product("Test8", 1250));
		repository.add(new Product("Test9", 2450));
		repository.add(new Product("Test10", 800));
		return repository;
	}

	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/product.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/product.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("product-client");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}
	
}
