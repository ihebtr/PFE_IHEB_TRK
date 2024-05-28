package tn.com.guru.gateway.model.tool;

import lombok.Data;

@Data
public class ResetPassword {

	private String newPassword;
	private String confrimPassword;
	private String oldPassword;
	
}
