package pl.piomin.service.sample.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.service.sample.client.config.ClientConfiguration;

@RestController
public class ClientController {

	@Autowired
	private ClientConfiguration conf;
	
	@GetMapping("/ping")
	public String ping() {
		return conf.showProperties();
	}
	
}
