package tn.com.guru.gateway.service;

import org.springframework.web.server.ServerWebExchange;

public interface IServerWebService {

	public String ipAddressFormWeb(ServerWebExchange exchange);
	
}
