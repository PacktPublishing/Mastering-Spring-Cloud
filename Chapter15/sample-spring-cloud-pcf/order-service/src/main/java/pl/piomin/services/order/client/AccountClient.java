package pl.piomin.services.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pl.piomin.services.order.model.Account;

@FeignClient(name = "account-service")
public interface AccountClient {

	@PostMapping("/")
	Account add(@RequestBody Account account);
	@PutMapping("/withdraw/{accountId}/{amount}")
	Account withdraw(@PathVariable("accountId") String id, @PathVariable("amount") int amount);

}
