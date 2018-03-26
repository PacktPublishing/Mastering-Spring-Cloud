package pl.piomin.services.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.account.model.Account;
import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.order.client.AccountClient;
import pl.piomin.services.order.client.CustomerClient;
import pl.piomin.services.order.client.ProductClient;
import pl.piomin.services.order.model.Order;
import pl.piomin.services.order.model.OrderStatus;
import pl.piomin.services.order.repository.OrderRepository;
import pl.piomin.services.product.model.Product;

@RestController
public class OrderController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderRepository repository;	
	@Autowired
	AccountClient accountClient;
	@Autowired
	CustomerClient customerClient;
	@Autowired
	ProductClient productClient;
	
	@PostMapping
	public Order prepare(@RequestBody Order order) {
		int price = 0;
		List<Product> products = productClient.find(order.getProductIds());
		Customer customer = customerClient.findByIdWithAccounts(order.getCustomerId());
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
		accountClient.withdraw(order.getAccountId(), order.getPrice());
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
		discount += (ordersNum*0.01);
		return (int) (price - (price * discount));
	}
	
}
