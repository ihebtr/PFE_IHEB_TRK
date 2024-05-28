package tn.com.guru.gateway.service.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.guru.gateway.model.LogData;
import tn.com.guru.gateway.model.tool.LogEvent;
import tn.com.guru.gateway.model.tool.SendObject;
import tn.com.guru.gateway.repository.ILogDataRepository;
import tn.com.guru.gateway.service.IAdmUserService;
import tn.com.guru.gateway.service.ICommonService;
import tn.com.guru.gateway.service.ILogDataService;
import tn.com.guru.gateway.tools.ConstanteWs;
import tn.com.guru.gateway.tools.UtilsWs;

@Service
public class LogDataService implements ILogDataService {

	private static final Logger logger = LogManager.getLogger(LogDataService.class);

	@Autowired
	private IAdmUserService admUserService;

	@Autowired
	private ILogDataRepository logDataRepository;

	@Autowired
	private UtilsWs utilsWs;

	@Autowired
	private ICommonService commonService;

	@Override
	public SendObject saveLogDataFromMicroService(LogEvent logEvent) {
		try {
			if (logEvent.getToken() != null) {
				LogData logData = new LogData(null, admUserService.getIdAdmUtilisateurByToken(logEvent.getToken()),
						(Timestamp) commonService.getDateSystemNow().getPayload(), logEvent.getUri(),
						logEvent.getHttpEvent(), logEvent.getIpAddress(), logEvent.getHttpCodeUser(),
						logEvent.getNameService());

				logData = logDataRepository.save(logData);
				return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
			} else
				return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, "No token");
		} catch (Exception e) {
			logger.error("Error LogDataService in method saveLogDataFromMicroService :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR, new JSONObject());
		}
	}

}
