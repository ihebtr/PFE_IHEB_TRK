package tn.com.guru.gateway.service.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import tn.com.guru.gateway.model.LogData;
import tn.com.guru.gateway.model.tool.SendObject;
import tn.com.guru.gateway.repository.ILogDataRepository;
import tn.com.guru.gateway.security.JwtSecurity;
import tn.com.guru.gateway.service.ICommonService;
import tn.com.guru.gateway.service.ISendWsService;
import tn.com.guru.gateway.tools.ConstanteWs;

@Service
public class SendWsService implements ISendWsService {

	private static final Logger logger = LogManager.getLogger(SendWsService.class);

	@Autowired
	private ILogDataRepository logDataRepository;
	
	@Autowired
	private JwtSecurity jwtSecurity;
	
	@Autowired
	private ICommonService commonService;

	@Value("${spring.application.name}")
	private String nameService;
	
	@Override
	public ResponseEntity<?> sendResult(ServerWebExchange exchange, SendObject so) {
		try {
			ServerHttpRequest request = exchange.getRequest();
			LogData logData = new LogData();
			logData.setIdAdmUser(jwtSecurity.getIdFromToken(request.getHeaders().getOrEmpty("Authorization").get(0)));
			logData.setDateLog((Timestamp) commonService.getDateSystemNow().getPayload());
			logData.setHttpMethod(request.getMethod().toString());
			logData.setUri(request.getPath().toString());
			logData.setResultWs(so.getCode());
			logData.setIpAddress(request.getRemoteAddress().getAddress()+":"+request.getRemoteAddress().getPort());
			logData.setNameService(nameService);
			logDataRepository.save(logData);
			return new ResponseEntity<>(so.getPayload().toString(), new HttpHeaders(), so.getHttp());
		} catch (Exception argEx) {
			logger.error("Error SendWsService in method sendResult :: " + argEx.toString());
			return new ResponseEntity<>(so.getPayload().toString(), new HttpHeaders(), so.getHttp());
		}
	}

	@Override
	@SuppressWarnings("finally")
	public ResponseEntity<?> sendResultException(ServerWebExchange exchange) {
		try {
			ServerHttpRequest request = exchange.getRequest();
			LogData logData = new LogData();
			logData.setIdAdmUser(jwtSecurity.getIdFromToken(request.getHeaders().getOrEmpty("Authorization").get(0)));
			logData.setDateLog((Timestamp) commonService.getDateSystemNow().getPayload());
			logData.setHttpMethod(request.getMethod().toString());
			logData.setUri(request.getPath().toString());
			logData.setResultWs(ConstanteWs._CODE_WS_ERROR);
			logData.setIpAddress(request.getRemoteAddress().getAddress()+":"+request.getRemoteAddress().getPort());
			logData.setNameService(nameService);
			logDataRepository.save(logData);
		} catch (Exception argEx) {
			logger.error("Error SendWsService in method sendResult :: " + argEx.toString());
		} finally {
			return new ResponseEntity<>(new JSONObject(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> sendResultPublic(SendObject so) {
		return new ResponseEntity<>(so.getPayload().toString(), new HttpHeaders(), so.getHttp());
	}
	
	@Override
	public ResponseEntity<?> sendResultPublicToMicro(Object so) {
		return new ResponseEntity<>( so, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@Override
	public ResponseEntity<?> sendResultExceptionPublic(ServerWebExchange request) {
		return new ResponseEntity<>(new JSONObject(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
