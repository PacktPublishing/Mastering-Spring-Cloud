package pl.piomin.services.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class AccountService {

	@Autowired
	RestTemplate template;
	
	@HystrixCommand(commandKey = "account-service.withdraw", commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
		@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
		@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
		@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
		@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
	})
	public void withdraw(Long accountId, int price) {
		template.put("http://account-service/withdraw/{id}/{amount}", null, accountId, price);
	}
	
}
