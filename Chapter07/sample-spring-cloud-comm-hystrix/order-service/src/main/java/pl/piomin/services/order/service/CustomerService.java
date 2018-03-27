package pl.piomin.services.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import pl.piomin.services.order.model.Customer;

@Service
public class CustomerService {

	@Autowired
	CacheManager cacheManager;
	@Autowired
	RestTemplate template;
	
	@CachePut("customers")
	@HystrixCommand(commandKey = "customer-service.findWithAccounts", fallbackMethod = "findCustomerWithAccountsFallback", 
		commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
		}
	)
	public Customer findCustomerWithAccounts(Long customerId) {
		Customer customer = template.getForObject("http://customer-service/withAccounts/{id}", Customer.class, customerId);
		return customer;
	}
	
	public Customer findCustomerWithAccountsFallback(Long customerId) {
		ValueWrapper w = cacheManager.getCache("customers").get(customerId);
		if (w != null) {
			return (Customer) w.get();
		} else {
			return new Customer();
		}
	}
}
