package tn.com.guru.gateway.service;

import org.springframework.web.server.ServerWebExchange;

import tn.com.guru.gateway.model.VAdmUser;
import tn.com.guru.gateway.model.tool.AuthRequest;
import tn.com.guru.gateway.model.tool.SendObject;

public interface IAdmUserService {

	public SendObject registerCompte(VAdmUser AdmUtilisateur, String ipAddress);

	public SendObject authenticateUser(AuthRequest authRequest, String ipAddress);

	public SendObject whoami(Long idAdmUtilisateur);
	
	public SendObject getIdAdmUtilisateurByTokenWs(String token);
	
	public SendObject getCurrentUserByTokenWs(String token);

	public Long getIdAdmUtilisateurByToken(ServerWebExchange exchange);

	public Long getIdAdmUtilisateurByToken(String token);

	public VAdmUser getVAdmUserById(Long id);
	
	public SendObject getCurrentUserIdByToken(String token);
	
	public SendObject getCurrentUserByToken(String token);


}
