package pl.piomin.services.order.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.piomin.services.messaging.Order;
import pl.piomin.services.order.messaging.OrderSender;
import pl.piomin.services.order.repository.OrderRepository;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	OrderRepository repository;
	@Autowired
	OrderSender sender;	
	
	@PostMapping
	public Order process(@RequestBody Order order) throws JsonProcessingException {
		Order o = repository.add(order);
		LOGGER.info("Order saved: {}", mapper.writeValueAsString(order));
		boolean isSent = sender.send(o);
		LOGGER.info("Order sent: {}", mapper.writeValueAsString(Collections.singletonMap("isSent", isSent)));
		return o;
	}
	
}
