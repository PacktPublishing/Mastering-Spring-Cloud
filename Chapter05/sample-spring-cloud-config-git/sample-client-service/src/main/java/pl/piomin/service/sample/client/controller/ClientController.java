package pl.piomin.service.sample.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/ping")
	public List<ServiceInstance> ping() {
		List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
		LOGGER.info("INSTANCES: count={}", instances.size());
		instances.stream().forEach(it -> LOGGER.info("INSTANCE: id={}, port={}", it.getServiceId(), it.getPort()));
		return instances;
	}
	
}
