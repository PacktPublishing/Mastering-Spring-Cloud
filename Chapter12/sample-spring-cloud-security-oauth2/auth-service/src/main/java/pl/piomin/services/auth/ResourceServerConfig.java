package pl.piomin.services.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.requestMatcher(new OrRequestMatcher(
//				new AntPathRequestMatcher("/"), 
//				new AntPathRequestMatcher("/admin/beans")
//			)).authorizeRequests().anyRequest().access("#oauth2.hasScope('read')");
//	}

}
