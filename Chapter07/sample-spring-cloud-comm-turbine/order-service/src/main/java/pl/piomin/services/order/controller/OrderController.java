package pl.piomin.services.order.controller;

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

import pl.piomin.services.order.model.Account;
import pl.piomin.services.order.model.Customer;
import pl.piomin.services.order.model.Order;
import pl.piomin.services.order.model.OrderStatus;
import pl.piomin.services.order.model.Product;
import pl.piomin.services.order.repository.OrderRepository;
import pl.piomin.services.order.service.AccountService;
import pl.piomin.services.order.service.CustomerService;
import pl.piomin.services.order.service.ProductService;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderRepository repository;	
	@Autowired
	AccountService accountService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
		
	@PostMapping
	public Order prepare(@RequestBody Order order) {
		LOGGER.info("New order: {}", order);
		int price = 0;
		List<Product> products = productService.findProductsByIds(order.getProductIds());
		Customer customer = customerService.findCustomerWithAccounts(order.getCustomerId());
		for (Product product : products) 
			price += product.getPrice();
		final int priceDiscounted = priceDiscount(price, customer);
		Optional<Account> account = customer.getAccounts().stream().filter(a -> (a.getBalance() > priceDiscounted)).findFirst();
		if (account.isPresent()) {
			order.setAccountId(account.get().getId());
			order.setStatus(OrderStatus.ACCEPTED);
			order.setPrice(priceDiscounted);
		} else {
			order.setStatus(OrderStatus.REJECTED);
		}
		return repository.add(order);
	}
	
	@PutMapping("/{id}")
	public Order accept(@PathVariable Long id) {
		final Order order = repository.findById(id);
		accountService.withdraw(order.getAccountId(), order.getPrice());
		order.setStatus(OrderStatus.DONE);
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
		if (ordersNum > 10)
			ordersNum = 10;
		discount += (ordersNum*0.01);
		return (int) (price - (price * discount));
	}
	
}
