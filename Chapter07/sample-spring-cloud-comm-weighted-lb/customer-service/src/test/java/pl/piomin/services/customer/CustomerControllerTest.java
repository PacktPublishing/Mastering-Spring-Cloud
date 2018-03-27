package pl.piomin.services.customer;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;

import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import io.specto.hoverfly.junit.rule.HoverflyRule;
import pl.piomin.services.customer.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerControllerTest {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerControllerTest.class);
	
	@Autowired
    TestRestTemplate template;
    
    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule
            .inSimulationMode(dsl(
            		service("account-service:8091")
            			.andDelay(200, TimeUnit.MILLISECONDS).forAll()
            			.get(startsWith("/customer/"))
            			.willReturn(success("[{\"id\":\"1\",\"number\":\"1234567890\",\"balance\":5000}]", "application/json")),
                	service("account-service:10091")
            			.andDelay(10000, TimeUnit.MILLISECONDS).forAll()
            			.get(startsWith("/customer/"))
            			.willReturn(success("[{\"id\":\"3\",\"number\":\"1234567892\",\"balance\":10000}]", "application/json")),
                    service("account-service:9091")
                    	.andDelay(50, TimeUnit.MILLISECONDS).forAll()
            			.get(startsWith("/customer/"))
            			.willReturn(success("[{\"id\":\"2\",\"number\":\"1234567891\",\"balance\":8000}]", "application/json"))))
            .printSimulationData();
    
    @Test
    public void testCustomerWithAccounts() {
    	int a = 0, b = 0, d = 0;
    	for (int i = 0; i < 1000; i++) {
    		try {
    			Customer c = template.getForObject("/withAccounts/{id}", Customer.class, 1);
            	LOGGER.info("Customer: {}", c);
            	if (c != null) {
	            	if (c.getAccounts().get(0).getId().equals(1L))
	            		a++;
	            	else
	            		b++;
            	}
    		} catch (Exception e) {
    			LOGGER.error("Error connecting with service", e);
    			d++;
    		}

		}
    	LOGGER.info("TEST RESULT: 8091={}, 9091={}, 10091={}", a, b, d);
    }
    
}
