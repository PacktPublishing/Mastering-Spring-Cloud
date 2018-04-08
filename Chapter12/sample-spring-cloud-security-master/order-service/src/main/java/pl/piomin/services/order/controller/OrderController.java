package pl.piomin.services.order.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.piomin.services.order.client.AccountClient;
import pl.piomin.services.order.client.CustomerClient;
import pl.piomin.services.order.client.ProductClient;
import pl.piomin.services.order.model.Account;
import pl.piomin.services.order.model.Customer;
import pl.piomin.services.order.model.Order;
import pl.piomin.services.order.model.OrderStatus;
import pl.piomin.services.order.model.Product;
import pl.piomin.services.order.repository.OrderRepository;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	OrderRepository repository;	
	@Autowired
	AccountClient accountClient;
	@Autowired
	CustomerClient customerClient;
	@Autowired
	ProductClient productClient;
	
	@PostMapping
	public Order prepare(@RequestBody Order order) throws JsonProcessingException {
		int price = 0;
		List<Product> products = productClient.findByIds(order.getProductIds());
		LOGGER.info("Products found: {}", mapper.writeValueAsString(products));
		Customer customer = customerClient.findByIdWithAccounts(order.getCustomerId());
		LOGGER.info("Customer found: {}", mapper.writeValueAsString(customer));
		for (Product product : products) 
			price += product.getPrice();
		final int priceDiscounted = priceDiscount(price, customer);
		LOGGER.info("Discounted price: {}", mapper.writeValueAsString(Collections.singletonMap("price", priceDiscounted)));
		Optional<Account> account = customer.getAccounts().stream().filter(a -> (a.getBalance() > priceDiscounted)).findFirst();
		if (account.isPresent()) {
			order.setAccountId(account.get().getId());
			order.setStatus(OrderStatus.ACCEPTED);
			order.setPrice(priceDiscounted);
			LOGGER.info("Account found: {}", mapper.writeValueAsString(account.get()));
		} else {
			order.setStatus(OrderStatus.REJECTED);
			LOGGER.info("Account not found: {}", mapper.writeValueAsString(customer.getAccounts()));
		}
		return repository.add(order);
	}
	
	@PutMapping("/{id}")
	public Order accept(@PathVariable Long id) throws JsonProcessingException {
		final Order order = repository.findById(id);
		LOGGER.info("Order found: {}", mapper.writeValueAsString(order));
		accountClient.withdraw(order.getAccountId(), order.getPrice());
		HashMap<String, Object> log = new HashMap<>();
		log.put("accountId", order.getAccountId());
		log.put("price", order.getPrice());
		LOGGER.info("Account modified: {}", mapper.writeValueAsString(log));
		order.setStatus(OrderStatus.DONE);
		LOGGER.info("Order status changed: {}", mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
		repository.update(order);
		return order;
	}
	
	private int priceDiscount(int price, Customer customer) {
		double discount = 0;
		switch (customer.getType()) {
		case REGULAR:
			discount += 0.05;
			break;
		case VIP:
			discount += 0.1;
			break;
			
		default:
			break;
		}
		int ordersNum = repository.countByCustomerId(customer.getId());
		discount += (ordersNum*0.01);
		return (int) (price - (price * discount));
	}
	
}
