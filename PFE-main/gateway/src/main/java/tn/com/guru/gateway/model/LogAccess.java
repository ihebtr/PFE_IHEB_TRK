package tn.com.guru.gateway.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "adm_log_access")

public class LogAccess implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "administration.seq_log_access", name = "administration.seq_log_access")
	@GeneratedValue(generator = "administration.seq_log_access", strategy = GenerationType.SEQUENCE)
	@Id

	@Column(name = "id", unique = true, nullable = false, precision = 22, scale = 0)
	private Long id;

	@Column(name = "code_access", nullable = false, length = 50)
	private String codeAccess;

	@Column(name = "date_auth", length = 29)
	private Date dateAuth;

	@Column(name = "id_adm_user", precision = 22, scale = 0)
	private Long idAdmUser;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "login", nullable = false)
	private String login;

	public LogAccess clone() throws CloneNotSupportedException {
		return (LogAccess) super.clone();
	}
}
