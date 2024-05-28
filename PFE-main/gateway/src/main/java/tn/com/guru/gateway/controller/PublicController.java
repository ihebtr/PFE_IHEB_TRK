package tn.com.guru.gateway.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import tn.com.guru.gateway.model.tool.AuthRequest;
import tn.com.guru.gateway.service.IAdmUserService;
import tn.com.guru.gateway.service.ICommonService;
import tn.com.guru.gateway.service.ISendWsService;
import tn.com.guru.gateway.service.IServerWebService;

@RestController
@CrossOrigin(origins = "*")
public class PublicController {

	private static final Logger logger = LogManager.getLogger(PublicController.class);

	@Autowired
	private IAdmUserService admUserService;

	@Autowired
	private ISendWsService sendWsService;

	@Autowired
	private ICommonService commonService;

	@Autowired
	private IServerWebService serverWebService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticationToApp(ServerWebExchange exchange,
			@RequestBody AuthRequest authenticationRequest) throws Exception {
		try {
			return sendWsService.sendResultPublic(admUserService.authenticateUser(authenticationRequest,
					serverWebService.ipAddressFormWeb(exchange)));
		} catch (Exception argEx) {
			logger.error("Error PublicController in method authenticationToApp :: " + argEx.toString());
			return sendWsService.sendResultPublic(null);
		}
	}

	@RequestMapping(value = "/dateNow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDateNow() throws Exception {
		try {
			return sendWsService.sendResultPublic(commonService.getDateSystemNowWs());
		} catch (Exception argEx) {
			logger.error("Error PublicController in method dateNow :: " + argEx.toString());
			return sendWsService.sendResultPublic(null);
		}
	}

	@RequestMapping(value = "/whoiam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> whoiamWs(ServerWebExchange exchange) {
		try {
			return sendWsService.sendResult(exchange,
					admUserService.whoami(admUserService.getIdAdmUtilisateurByToken(exchange)));
		} catch (Exception argEx) {
			logger.error("Error AccountController in method whoiam" + argEx.toString());
			return sendWsService.sendResultException(exchange);
		}
	}
}
