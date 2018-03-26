package pl.piomin.service.sample.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ClientConfiguration {

	@Value("${sample.string.property}")
	private String sampleStringProperty;
	@Value("${sample.int.property}")
	private int sampleIntProperty;
	
	public String showProperties() {
		return String.format("Hello from %s %d", sampleStringProperty, sampleIntProperty);
	}

}
