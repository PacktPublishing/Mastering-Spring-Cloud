package pl.piomin.services.customer.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import pl.piomin.services.account.contract.AccountService;

@FeignClient(name = "account-service")
public interface AccountClient extends AccountService {
	
}
