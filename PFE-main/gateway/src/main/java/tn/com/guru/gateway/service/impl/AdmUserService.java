package tn.com.guru.gateway.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import tn.com.guru.gateway.model.VAdmUser;
import tn.com.guru.gateway.model.tool.AuthRequest;
import tn.com.guru.gateway.model.tool.SendObject;
import tn.com.guru.gateway.repository.IVAdmUserRepository;
import tn.com.guru.gateway.security.JwtSecurity;
import tn.com.guru.gateway.service.IAdmUserService;
import tn.com.guru.gateway.service.ILogAccessService;
import tn.com.guru.gateway.tools.ConstanteWs;
import tn.com.guru.gateway.tools.UtilsWs;

@Service
public class AdmUserService implements IAdmUserService {

	private static final Logger logger = LogManager.getLogger(AdmUserService.class);

	@Autowired
	private IVAdmUserRepository vAdmUserRepository;

	@Autowired
	private UtilsWs utilsWs;

	@Autowired
	private JwtSecurity jwtSecurity;

	@Autowired
	private ILogAccessService logAccessService;

	@Override
	public SendObject registerCompte(VAdmUser AdmUtilisateur, String ipAddress) {
		return null;
	}

	@Override
	public SendObject authenticateUser(AuthRequest authRequest, String ipAddress) {
		String token = null;
		VAdmUser vAdmUser = null;
		try {
			vAdmUser = vAdmUserRepository.findVAdmUserByLogin(authRequest.getLogin());
			System.out.println("user"+ authRequest.getLogin()+ vAdmUser);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (!encoder.matches(authRequest.getPassword(), vAdmUser.getPassword()))
				return utilsWs.resultWs(ConstanteWs._CODE_WS_USER_ERROR_AUTH, null);

			String code = vAdmUser.getIsActif() ? ConstanteWs._CODE_WS_SUCCESS
					: ConstanteWs._CODE_WS_SUCCESS_WAIT_PERMISSION;

			token = jwtSecurity.generate(vAdmUser, "ACCESS");
			if (token != null && code.equals(ConstanteWs._CODE_WS_SUCCESS)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("token", token);
				logAccessService.saveLogAccess(code, vAdmUser.getIdAdmUser(), vAdmUser.getLogin(), ipAddress);
				return utilsWs.resultWs(code, jsonObject);
			} else {
				return utilsWs.resultWs(code, new JSONObject());
			}
		} catch (Exception e) {
			logger.error("Error AdmUtilisateurService in method authenticateUser :: " + e.toString());
		} finally {
			if (token == null)
				logAccessService.saveLogAccess(ConstanteWs._CODE_WS_USER_ERROR_AUTH,
						vAdmUser != null ? vAdmUser.getIdAdmUser() : null, authRequest.getLogin(), ipAddress);
		}
		return utilsWs.resultWs(ConstanteWs._CODE_WS_USER_ERROR_AUTH, new JSONObject());
	}

	@Override
	public SendObject whoami(Long idAdmUser) {
		try {
			if (idAdmUser == null)
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
			VAdmUser vAdmUser = this.getVAdmUserById(idAdmUser);
			if (vAdmUser.getIdAdmUser() == null)
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", vAdmUser.getIdAdmUser());
			jsonObject.put("nom", vAdmUser.getNom());
			jsonObject.put("prenom", vAdmUser.getPrenom());
			jsonObject.put("email", vAdmUser.getMail());
			jsonObject.put("isModif", vAdmUser.getIsModif());
			jsonObject.put("status", vAdmUser.getStatus());
			//houni badlet
			return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS,new JSONObject(vAdmUser));
		} catch (Exception e) {
			logger.error("Error VAdmUserService in method whoami :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public VAdmUser getVAdmUserById(Long id) {
		try {
			if (id == null)
				return new VAdmUser();
			VAdmUser user = vAdmUserRepository.findVAdmUserById(id);
			return user == null ? new VAdmUser() : user;
		} catch (Exception e) {
			logger.error("Error VAdmUserService in method getVAdmUserById :: " + e.toString());
			return new VAdmUser();
		}
	}

	@Override
	public Long getIdAdmUtilisateurByToken(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		return jwtSecurity.getIdFromToken(request.getHeaders().getOrEmpty("Authorization").get(0));
	}

	public Long getIdAdmUtilisateurByToken(String token) {
		token = token.startsWith("Bearer ") ? token.substring(7, token.length()) : token;
		return jwtSecurity.getIdFromToken(token);
	}

	@Override
	public SendObject getIdAdmUtilisateurByTokenWs(String token) {
		token = token.startsWith("Bearer ") ? token.substring(7, token.length()) : token;
		Long id = jwtSecurity.getIdFromToken(token);
		return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(id));
	}

	@Override
	public SendObject getCurrentUserByTokenWs(String token) {
		token = token.startsWith("Bearer ") ? token.substring(7, token.length()) : token;
		Long id = jwtSecurity.getIdFromToken(token);
		VAdmUser user = this.getVAdmUserById(id);
		return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(user));
	}

	@Override
	public SendObject getCurrentUserIdByToken(String token) {
		try {
			Long id = this.jwtSecurity.getIdFromToken(token);
			return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, id);
		} catch (Exception e) {
			logger.error("Error AdmUtilisateurService in method getCurrentUserIdByToken :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, null);
		}
	}

	@Override
	public SendObject getCurrentUserByToken(String token) {
		try {
			Object user = this.jwtSecurity.getUserInfoFromToken(token);
			return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, user);
		} catch (Exception e) {
			logger.error("Error AdmUtilisateurService in method getCurrentUserByToken :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, null);
		}
	}


}
