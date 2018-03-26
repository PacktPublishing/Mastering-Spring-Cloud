package pl.piomin.services.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import pl.piomin.services.order.repository.OrderRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(OrderApplication.class).web(true).run(args);
	}
	
	@Bean
	OrderRepository repository() {
		return new OrderRepository();
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    loggingFilter.setMaxPayloadLength(1000);
	    loggingFilter.setAfterMessagePrefix("REQ:");
	    return loggingFilter;
	}
	
	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}
	
}
