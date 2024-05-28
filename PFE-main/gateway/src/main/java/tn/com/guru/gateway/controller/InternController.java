package tn.com.guru.gateway.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.server.ServerWebExchange;
import tn.com.guru.gateway.model.tool.LogEvent;
import tn.com.guru.gateway.service.IAdmUserService;
import tn.com.guru.gateway.service.ILogDataService;
import tn.com.guru.gateway.service.ISendWsService;

@Hidden
@RestController
@RequestMapping("/intern")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InternController {

	private static final Logger logger = LogManager.getLogger(InternController.class);

	@Autowired
	private ISendWsService sendWsService;

	@Autowired
	private IAdmUserService admUserService;

	@Autowired
	private ILogDataService logDataService;

	@PostMapping(value = "/logData/traceability", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLogTraceabilityFromServices(@RequestBody LogEvent logEvent) {
		try {
			return sendWsService.sendResultPublic(logDataService.saveLogDataFromMicroService(logEvent));
		} catch (Exception argEx) {
			logger.error(
					"Error LogDataAdminiController in method getLogTraceabilityFromServices :: " + argEx.toString());
			return sendWsService.sendResultExceptionPublic(null);
		}
	}

	@PostMapping(value = "/current/user/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCurrentUserIdByToken(@RequestBody String token) {
		try {
			return sendWsService.sendResultPublic(admUserService.getCurrentUserIdByToken(token));
		} catch (Exception argEx) {
			logger.error("Error InternController in method getCurrentUserIdByToken :: " + argEx.toString());
			return sendWsService.sendResultPublic(null);
		}
	}


	@PostMapping(value = "/current/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCurrentUserByToken(@RequestBody String token) {
		try {
			return sendWsService.sendResultPublic(admUserService.getCurrentUserByTokenWs(token));
		} catch (Exception argEx) {
			logger.error("Error InternController in method getCurrentUserIdByToken :: " + argEx.toString());
			return sendWsService.sendResultPublic(null);
		}
	}


}
