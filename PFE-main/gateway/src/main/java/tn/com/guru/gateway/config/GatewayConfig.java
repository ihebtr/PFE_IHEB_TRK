package tn.com.guru.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tn.com.guru.gateway.security.AuthenticationFilter;

@Configuration
@EnableHystrix
@Profile({"prod", "test"})
public class GatewayConfig {

	@Autowired
	AuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes() 
				.route("gateway-service", r -> r.path("/gateway/**").filters(f -> 
					f.filter(filter) 
					.stripPrefix(1)
					.setResponseHeader("Access-Control-Allow-Origin", "*"))
						.uri("lb://gateway-service"))
				.route("template-service", r -> r.path("/template/**").filters(f -> 
					f.filter(filter) 
					.stripPrefix(1)
					.setResponseHeader("Access-Control-Allow-Origin", "*"))
						.uri("lb://template-service"))
				.route("administration-service", r -> r.path("/administration/**").filters(f -> 
					f.filter(filter) 
					.stripPrefix(1)
					.setResponseHeader("Access-Control-Allow-Origin", "*"))
						.uri("lb://administration-service"))
				.route("ecole-service", r -> r.path("/ecole/**").filters(f ->
								f.filter(filter)
										.stripPrefix(1)
										.setResponseHeader("Access-Control-Allow-Origin", "*"))
						.uri("lb://ecole-service"))
				.build();
	}

}
