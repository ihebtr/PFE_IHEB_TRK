package tn.com.guru.gateway.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.guru.gateway.model.LogAccess;
import tn.com.guru.gateway.repository.ILogAccessRepository;
import tn.com.guru.gateway.service.ILogAccessService;

@Service
public class LogAccessService implements ILogAccessService {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAccessService.class);
	
	@Autowired
	private ILogAccessRepository logAccessRepository;

	@Override
	public void saveLogAccess(String codeAccess, Long idAdmUser, String login, String ipAddress) {
		try {
			LogAccess logAccess = new LogAccess();
			logAccess.setCodeAccess(codeAccess);
			logAccess.setDateAuth(new Timestamp(new Date().getTime()));
			logAccess.setLogin(login);
			logAccess.setIdAdmUser(idAdmUser != null ? idAdmUser : null);
			logAccess.setIpAddress(ipAddress);
			logAccessRepository.save(logAccess);
		} catch (Exception e) {
			logger.error("Error LogAccessService in method saveLogAccess :: "+e.toString());
		}
	}

}
