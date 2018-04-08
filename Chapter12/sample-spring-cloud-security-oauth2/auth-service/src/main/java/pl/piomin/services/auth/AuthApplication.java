package pl.piomin.services.auth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
//@EnableAuthorizationServer
public class AuthApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AuthApplication.class).web(true).run(args);
	}
	
}
