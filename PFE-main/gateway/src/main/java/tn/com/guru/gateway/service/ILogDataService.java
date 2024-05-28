package tn.com.guru.gateway.service;

import tn.com.guru.gateway.model.tool.LogEvent;
import tn.com.guru.gateway.model.tool.SendObject;

public interface ILogDataService {
	
	public SendObject saveLogDataFromMicroService(LogEvent logEvent);

}
