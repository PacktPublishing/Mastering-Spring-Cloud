package pl.piomin.services.account;

import java.io.File;

import javax.net.ssl.SSLContext;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SSLConfigServiceBootstrapConfiguration {

	@Autowired
	ConfigClientProperties properties;

	@Bean
	public ConfigServicePropertySourceLocator configServicePropertySourceLocator() throws Exception {
		final char[] password = "123456".toCharArray();
		final File keyStoreFile = new File("src/main/resources/discovery.jks");
		SSLContext sslContext = SSLContexts.custom()
				.loadKeyMaterial(keyStoreFile, password, password)
				.loadTrustMaterial(keyStoreFile).build();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(properties);
		configServicePropertySourceLocator.setRestTemplate(new RestTemplate(requestFactory));
		return configServicePropertySourceLocator;
	}
}
