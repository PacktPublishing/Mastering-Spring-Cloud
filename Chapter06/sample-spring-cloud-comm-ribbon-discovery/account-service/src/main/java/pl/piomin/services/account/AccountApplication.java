package pl.piomin.services.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import pl.piomin.services.account.model.Account;
import pl.piomin.services.account.repository.AccountRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(AccountApplication.class).web(true).run(args);
	}
	
	@Bean
	AccountRepository repository() {
		AccountRepository repository = new AccountRepository();
		repository.add(new Account("1234567890", 5000, 1L));
		repository.add(new Account("1234567891", 5000, 1L));
		repository.add(new Account("1234567892", 0, 1L));
		repository.add(new Account("1234567893", 5000, 2L));
		repository.add(new Account("1234567894", 0, 2L));
		repository.add(new Account("1234567895", 5000, 2L));
		repository.add(new Account("1234567896", 0, 3L));
		repository.add(new Account("1234567897", 5000, 3L));
		repository.add(new Account("1234567898", 5000, 3L));
		return repository;
	}

}
