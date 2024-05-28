package tn.com.guru.gateway.model;

import java.sql.Timestamp;

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
@Table(name = "adm_log_data")

public class LogData implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "administration.seq_log_data", name = "administration.seq_log_data")
	@GeneratedValue(generator = "administration.seq_log_data", strategy = GenerationType.SEQUENCE)
	@Id

	@Column(name = "id", unique = true, nullable = false, precision = 22, scale = 0)
	private Long id;


	@Column(name = "id_adm_user", nullable = false, precision = 22, scale = 0)
	private Long idAdmUser;
	
	@Column(name = "date_log", nullable = false, length = 29)
	private Timestamp dateLog;

	@Column(name = "uri", nullable = false)
	private String uri;
	
	@Column(name = "http_method", nullable = false, length = 50)
	private String httpMethod;
	
	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "result_ws", nullable = false)
	private String resultWs;
	
	@Column(name = "name_service", nullable = false)
	private String nameService;
	
	public LogData clone() throws CloneNotSupportedException {
		return (LogData) super.clone();
	}
}
