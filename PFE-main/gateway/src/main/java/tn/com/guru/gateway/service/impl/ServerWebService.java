package tn.com.guru.gateway.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import tn.com.guru.gateway.service.IServerWebService;

@Service
public class ServerWebService implements IServerWebService {

	private static final Logger logger = LogManager.getLogger(ServerWebService.class);
	
	@Override
	public String ipAddressFormWeb(ServerWebExchange exchange) {
		try {
			ServerHttpRequest request = exchange.getRequest();
			return request.getRemoteAddress().getAddress()+":"+request.getRemoteAddress().getPort();
		} catch (Exception e) {
			logger.error("Error ServerWebService in method ipAddressFormWeb :: " + e.toString());
			return null;
		}
	}

}
