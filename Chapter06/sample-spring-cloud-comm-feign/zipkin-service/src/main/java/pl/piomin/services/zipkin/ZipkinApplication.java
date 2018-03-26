package pl.piomin.services.zipkin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipkinApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZipkinApplication.class).web(true).run(args);
	}

}
