package pl.piomin.service.sample.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
	
	@GetMapping("/ping")
	public String ping() {
		return "I'm alive";
	}
	
}
