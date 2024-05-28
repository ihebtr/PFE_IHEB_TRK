package tn.com.guru.gateway.service;

public interface ILogAccessService {

	public void saveLogAccess(String codeAccess, Long idAdmUser, String login, String ipAddress);

}
