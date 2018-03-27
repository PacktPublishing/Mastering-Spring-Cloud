package pl.piomin.services.customer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import pl.piomin.services.customer.model.Account;
import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CacheManager cacheManager;
	@Autowired
	RestTemplate template;
	@Autowired
	CustomerRepository repository;

	public Customer add(Customer customer) {
		return repository.add(customer);
	}

	public Customer update(Customer customer) {
		return repository.update(customer);
	}

	public Customer findById(Long id) {
		return repository.findById(id);
	}

	public List<Customer> find(List<Long> ids) {
		return repository.find(ids);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	@CachePut("accounts")
	@HystrixCommand(fallbackMethod = "findCustomerAccountsFallback", 
		commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
		}
	)
	public List<Account> findCustomerAccounts(Long id) {
		Account[] accounts = template.getForObject("http://account-service/customer/{customerId}", Account[].class, id);
		return Arrays.stream(accounts).collect(Collectors.toList());
	}
	
	public List<Account> findCustomerAccountsFallback(Long id) {
		ValueWrapper w = cacheManager.getCache("accounts").get(id);
		if (w != null) {
			return (List<Account>) w.get();
		} else {
			return new ArrayList<>();
		}
	}
	
}
