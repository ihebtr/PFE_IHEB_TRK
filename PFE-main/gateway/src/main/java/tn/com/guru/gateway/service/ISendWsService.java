package tn.com.guru.gateway.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;

import tn.com.guru.gateway.model.tool.SendObject; 

public interface ISendWsService {

	public ResponseEntity<?> sendResult(ServerWebExchange exchange, SendObject so);
	
	public ResponseEntity<?> sendResultException(ServerWebExchange exchange);
	
	public ResponseEntity<?> sendResultPublic(SendObject so);

	public ResponseEntity<?> sendResultPublicToMicro(Object so);
	
	public ResponseEntity<?> sendResultExceptionPublic(ServerWebExchange request);

}
