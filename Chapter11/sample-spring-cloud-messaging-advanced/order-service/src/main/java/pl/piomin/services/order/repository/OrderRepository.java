package pl.piomin.services.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pl.piomin.services.messaging.Order;

public class OrderRepository {

	private List<Order> orders = new ArrayList<>();
	
	public Order add(Order order) {
		order.setId((long) (orders.size()+1));
		orders.add(order);
		return order;
	}
	
	public Order update(Order order) {
		orders.set(order.getId().intValue() - 1, order);
		return order;
	}
	
	public Order findById(Long id) {
		Optional<Order> order = orders.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (order.isPresent())
			return order.get();
		else
			return null;
	}
	
	public void delete(Long id) {
		orders.remove(id.intValue());
	}
	
	public List<Order> find(List<Long> ids) {
		return orders.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
	}
	
	public int countByCustomerId(Long customerId) {
		return (int) orders.stream().filter(p -> p.getCustomerId().equals(customerId)).count();
	}
}
