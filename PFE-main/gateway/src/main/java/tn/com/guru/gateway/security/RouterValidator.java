package tn.com.guru.gateway.security;

import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Profile({"prod", "test"})
public class RouterValidator {

	public static final List<String> openApiEndpoints = Arrays.asList("register", "authenticate", "dateNow", "intern");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().startsWith(uri));

}