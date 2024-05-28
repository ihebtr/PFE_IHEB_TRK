package tn.com.guru.gateway.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "adm_user")
public class VAdmUser {

	@Id
	@Column(name = "ID", unique = true, length = 255)
	private Long idAdmUser;

	@Column(name = "LOGIN", unique = true, length = 255)
	private String login;

	@Column(name = "pwd")
	private String password;

	@Column(name = "prenom", length = 50)
	private String prenom;

	@Column(name = "nom", length = 50)
	private String nom;

	@Column(name = "user_name", length = 50)
	private String username;

	@Column(name = "dt_naissance", length = 13)
	private Timestamp dtNaissance;

	@Column(name = "sexe")
	private Integer sexe;

	@Column(name = "sexe_str_ar")
	private String sexeStrAr;

	@Column(name = "sexe_str_en")
	private String sexeStrEn;

	@Column(name = "is_active")
	private Boolean isActif;

	@Column(name = "id_adm_personnel")
	private Long idAdmPersonnel;

	@Column(name = "dt_ajout")
	private Timestamp dt_ajout;

	@Column(name = "dt_maj")
	private Timestamp dt_maj;

	@Column(name = "matricule")
	private String matricule;

	@Column(name = "mail")
	private String mail;

	@Column(name = "num_tel")
	private String num_tel;

	@Column(name = "cin")
	private String cin;

	@Column(name = "ismodif")
	private Boolean isModif;

	@Column(name = "status")
	private String status;
}