package pl.piomin.services.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AccountApplication.class).web(true).run(args);
	}

}
